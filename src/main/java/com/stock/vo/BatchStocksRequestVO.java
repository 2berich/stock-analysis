package com.stock.vo;

import lombok.Data;

import java.util.Set;

/**
 * @author lei
 * @date 2019/4/20
 **/
@Data
public class BatchStocksRequestVO {

    private Set<String> stockCodes;
}
