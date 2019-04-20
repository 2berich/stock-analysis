package com.stock.service;

import com.stock.vo.AddStocksRequestVO;
import com.stock.vo.MainMonitorDetailVO;
import com.stock.vo.StockResponseVO;

/**
 * @author lei
 * @date 2019/4/20
 **/
public interface StockService {

    void handlerResult(MainMonitorDetailVO mainMonitorDetailVO);

    void fetchBehaviours();

    void fetchBehaviours(String stockCode);

    void addStocks(AddStocksRequestVO addStocksRequestVO);

    /**
     * 推荐计算
     */
    StockResponseVO suggestCacl(String stockCode);

    void setTimes(Integer times);

    void setThreshold(Integer threshold);

    void setProfitLimit(Integer profitLimit);
}
