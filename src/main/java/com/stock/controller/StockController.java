package com.stock.controller;

import com.stock.service.StockService;
import com.stock.vo.AddStocksRequestVO;
import com.stock.vo.BatchStocksRequestVO;
import com.stock.vo.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lei
 * @date 2019/4/20
 **/
@RestController
@RequestMapping(value = "/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * 设置手数 即 大于多少手纳入统计
     * @param times
     * @return
     */
    @RequestMapping("setTimes")
    public JSONResult setTimes(@RequestParam Integer times) {
        stockService.setTimes(times);
        return new JSONResult();
    }

    /**
     * 设置阀值 即一手大于多少才进行计算
     * @param threshold
     * @return
     */
    @RequestMapping("setThreshold")
    public JSONResult setThreshold(@RequestParam Integer threshold) {
        stockService.setThreshold(threshold);
        return new JSONResult();
    }

    /**
     * 设置涨幅限制 涨幅小于才返回参考
     * @param profitLimit
     * @return
     */
    @RequestMapping("setProfitLimit")
    public JSONResult setProfitLimit(@RequestParam Integer profitLimit) {
        stockService.setProfitLimit(profitLimit);
        return new JSONResult();
    }

    /**
     * 增加股票
     * @param addStocksRequestVO
     * @return
     */
    @RequestMapping("addStocks")
    public JSONResult addStocks(@RequestBody AddStocksRequestVO addStocksRequestVO) {
        stockService.addStocks(addStocksRequestVO);
        return new JSONResult();
    }

    /**
     * 设置待监控股票
     * @param BatchStocksRequestVO
     * @return
     */
    @RequestMapping("batchMarkHot")
    public JSONResult batchEnableHot(@RequestBody BatchStocksRequestVO requestVO) {
        final AddStocksRequestVO stocksRequestVO = AddStocksRequestVO.of(requestVO);
        stocksRequestVO.setHot(true);
        stockService.addStocks(stocksRequestVO);
        return new JSONResult();
    }

    /**
     * 取消待监控股票
     * @param BatchStocksRequestVO
     * @return
     */
    @RequestMapping("batchDisableHot")
    public JSONResult batchDisableHot(@RequestBody BatchStocksRequestVO requestVO) {
        final AddStocksRequestVO stocksRequestVO = AddStocksRequestVO.of(requestVO);
        stocksRequestVO.setHot(false);
        stockService.addStocks(stocksRequestVO);
        return new JSONResult();
    }


    /**
     * 获取最新信息
     * @param BatchStocksRequestVO
     * @return
     */
    @RequestMapping("fetchNow")
    public JSONResult batchDisableHot(@RequestParam String stockCode) {
        stockService.fetchBehaviours(stockCode);
//        stockService.suggestCacl(stockCode);
        return new JSONResult();
    }

}
