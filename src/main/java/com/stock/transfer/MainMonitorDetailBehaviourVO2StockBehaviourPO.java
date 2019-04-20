package com.stock.transfer;

import com.google.common.base.Function;
import com.stock.constants.Constant;
import com.stock.dao.po.StockBehaviourPO;
import com.stock.enums.TradeNatureEnum;
import com.stock.util.DateUtil;
import com.stock.vo.MainMonitorDetailBehaviourVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author lei
 * @date 2019/4/20
 **/
public class MainMonitorDetailBehaviourVO2StockBehaviourPO implements Function<MainMonitorDetailBehaviourVO, StockBehaviourPO> {

    private String stockCode;

    private String stockName;

    public MainMonitorDetailBehaviourVO2StockBehaviourPO(String stockCode, String stockName) {
        this.stockCode = stockCode;
        this.stockName = stockName;
    }

    @Override
    public StockBehaviourPO apply(MainMonitorDetailBehaviourVO input) {
        if (input == null) {
            return null;
        }
        final StockBehaviourPO stockBehaviourPO = new StockBehaviourPO();
        final String volumeStr = StringUtils.removeEnd(input.getVolume(), Constant.HAND_SYMBOL);
        Integer volume = 0;
        if (NumberUtils.isNumber(volumeStr)) {
            volume = Integer.valueOf(volumeStr);

        }
        //低于一定值，不统计
        if (volume < Constant.VOLUME_LIMIT) {
            return null;
        }

        BeanUtils.copyProperties(input, stockBehaviourPO);
        stockBehaviourPO.setId(generateId(input));
        stockBehaviourPO.setStockcode(stockCode);
        stockBehaviourPO.setStockname(stockName);
        stockBehaviourPO.setVolume(volume);
        stockBehaviourPO.setTradeNature(TradeNatureEnum.resolveOf(input.getNature()));

        return stockBehaviourPO;
    }

    private String generateId(MainMonitorDetailBehaviourVO input) {
        return String.format("%s_%s_%s", stockCode, DateUtil.getNowDateFmt(), input.getCtime());

    }
}
