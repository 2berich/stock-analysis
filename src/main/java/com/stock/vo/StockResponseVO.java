package com.stock.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author lei
 * @date 2019/4/20
 **/
@Builder
@Data
public class StockResponseVO {

    private String stockCode;

    private String stockName;
}
