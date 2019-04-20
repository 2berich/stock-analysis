package com.stock.transfer;

import com.google.common.base.Function;
import com.stock.constants.Constant;
import com.stock.dao.po.StockPO;
import com.stock.vo.MainMonitorDetailTitleVO;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author lei
 * @date 2019/4/20
 **/
public class MainMonitorDetailTitleVO2StockPO implements Function<MainMonitorDetailTitleVO, StockPO>{

    @Override
    public StockPO apply(MainMonitorDetailTitleVO input) {
        final StockPO stockPO = new StockPO();
        BeanUtils.copyProperties(input, stockPO);
        stockPO.setId(input.getStockcode());
        String profitStr = StringUtils.removeEnd(input.getProfit(), Constant.PERCENT_SYMBOL);
        if (NumberUtils.isNumber(profitStr)) {
            stockPO.setProfit(Double.valueOf(profitStr));
        }
        return stockPO;
    }
}
