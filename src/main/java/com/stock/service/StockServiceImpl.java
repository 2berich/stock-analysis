package com.stock.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stock.config.StockConfig;
import com.stock.constants.Constant;
import com.stock.dao.po.StockBehaviourPO;
import com.stock.dao.po.StockPO;
import com.stock.dao.po.StockPriceChangePO;
import com.stock.dao.repository.StockBehaviourRepository;
import com.stock.dao.repository.StockPriceChangeRepository;
import com.stock.dao.repository.StockRepository;
import com.stock.enums.TradeNatureEnum;
import com.stock.transfer.MainMonitorDetailBehaviourVO2StockBehaviourPO;
import com.stock.transfer.MainMonitorDetailTitleVO2StockPO;
import com.stock.util.HttpClientUtil;
import com.stock.util.MapUrlParamsUtils;
import com.stock.vo.AddStocksRequestVO;
import com.stock.vo.MainMonitorDetailVO;
import com.stock.vo.StockResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lei
 * @date 2019/4/20
 **/
@Slf4j
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockConfig stockConfig;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockBehaviourRepository stockBehaviourRepository;

    @Autowired
    private StockPriceChangeRepository stockPriceChangeRepository;

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 100,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());


    @Override
    public void addStocks(AddStocksRequestVO addStocksRequestVO) {
        log.info("Add stocks. {}", addStocksRequestVO);
        if (addStocksRequestVO != null && CollectionUtils.isNotEmpty(addStocksRequestVO.getStockCodes())) {
            saveStocks(addStocksRequestVO.getStockCodes(), addStocksRequestVO.getHot());
        }
    }

    private void saveStocks(Set<String> stockCodes, Boolean hot) {
        List<StockPO> stockPOs = Lists.newArrayList();
        for (String stockCode : stockCodes) {
            final StockPO stockPO = new StockPO();
            stockPO.setId(stockCode);
            stockPO.setStockcode(stockCode);
            stockPO.setHot(BooleanUtils.isTrue(hot));
        }
        stockRepository.saveAll(stockPOs);
    }

    @Override
    public void fetchBehaviours() {
        final List<String> stockCodes = stockRepository.findHotStockCodes();
        if (CollectionUtils.isNotEmpty(stockCodes)) {
            for (String stockCode : stockCodes) {
                threadPoolExecutor.execute(() -> fetchBehaviours(stockCode));
            }
        }
    }

    @Override
    public void fetchBehaviours(String stockCode) {
        final Map<String, Object> map = Maps.newHashMap();
        map.put("op", Constant.OP_MAIN_MONITOR_DETAIL);
        map.put("stockcode", stockCode);
        map.put("userid", Constant.USER_ID);
        final String params = MapUrlParamsUtils.getUrlParamsByMap(map);
        try {
            String response = HttpClientUtil.doGet(Constant.MAI_MONITOR_DETAIL_URL + "?" + params, 2);
            response = StringUtils.remove(response, Constant.FILTER_CHAR);
            final MainMonitorDetailVO mainMonitorDetailVO = JSONObject.parseObject(response, MainMonitorDetailVO.class);
            mainMonitorDetailVO.setStockCode(stockCode);
            handlerResult(mainMonitorDetailVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public StockResponseVO suggestCacl(String stockCode) {
        final List<StockBehaviourPO> stockBehaviourPos = stockBehaviourRepository.suggestBehevioursByStockCode(stockCode, TradeNatureEnum.ACTIVE_BUY, stockConfig.threshold);
        if (stockBehaviourPos.size() >= stockConfig.times) {
            final StockBehaviourPO stockBehaviourPO = stockBehaviourPos.get(0);

            log.info("{}({}) 主动主买大于: {}手", stockBehaviourPO.getStockname(), stockBehaviourPO.getStockcode(), stockConfig.threshold);
            final StockPO stockPO = stockRepository.findByIdAndProfitLessThanEqual(stockCode, stockConfig.profitLimit);
            if (stockPO != null) {
                log.info("{}({}) 主动主买大于: {}手, 涨幅: {}", stockBehaviourPO.getStockname(), stockBehaviourPO.getStockcode(), stockConfig.threshold, stockPO.getProfit());
            }
            return StockResponseVO.builder().stockCode(stockBehaviourPO.getStockcode()).stockName(stockBehaviourPO.getStockname()).build();
        }
        return StockResponseVO.builder().stockCode(stockCode).build();
    }


    @Override
    public void setTimes(Integer times) {
        log.info("config set times: {}", times);
        if (times > 0) {
            stockConfig.times = times;
        }
    }

    @Override
    public void setThreshold(Integer threshold) {
        log.info("config set threshold: {}", threshold);
        if (threshold > Constant.VOLUME_LIMIT && threshold <= Constant.VOLUME_MAX_LIMIT) {
            stockConfig.threshold = threshold;
        }
    }

    @Override
    public void setProfitLimit(Integer profitLimit) {
        log.info("config set profitLimit: {}", profitLimit);
        if (profitLimit > Constant.PROFIT_MIN && profitLimit < Constant.PROFIT_MAX) {
            stockConfig.profitLimit = profitLimit;
        }
    }

    @Override
    public void handlerResult(MainMonitorDetailVO mainMonitorDetailVO) {
        if (mainMonitorDetailVO == null) {
            return;
        }
        String stockName = null;
        if (mainMonitorDetailVO.getTitle() != null) {
            stockName = mainMonitorDetailVO.getTitle().getStockname();
            final StockPO stockPO = new MainMonitorDetailTitleVO2StockPO().apply(mainMonitorDetailVO.getTitle());
            stockPO.setHot(true);
            stockRepository.save(stockPO);
        }

        if (CollectionUtils.isNotEmpty(mainMonitorDetailVO.getList())) {
            final List<StockBehaviourPO> behaviourPOs = Lists.transform(mainMonitorDetailVO.getList(), new MainMonitorDetailBehaviourVO2StockBehaviourPO(mainMonitorDetailVO.getStockCode(), stockName));
            stockBehaviourRepository.saveAll(behaviourPOs);
        }

        if (CollectionUtils.isNotEmpty(mainMonitorDetailVO.getPricechange())) {
            List<StockPriceChangePO> stockPriceChangePOs = Lists.newArrayList();
            for (JSONObject jsonObject : mainMonitorDetailVO.getPricechange()) {
                final String timeline = jsonObject.getObject("1", String.class);
                final Double profit = jsonObject.getObject("2525646", Double.class);
                if (timeline != null && profit != null) {
                    StockPriceChangePO stockPriceChangePO = new StockPriceChangePO();
                    stockPriceChangePO.setId(timeline);
                    stockPriceChangePO.setStockcode(mainMonitorDetailVO.getStockCode());
                    stockPriceChangePO.setStockname(stockName);
                    stockPriceChangePO.setTimeline(timeline);
                    stockPriceChangePO.setProfit(profit);
                    stockPriceChangePOs.add(stockPriceChangePO);
                }
            }
            stockPriceChangeRepository.saveAll(stockPriceChangePOs);
        }
    }
}
