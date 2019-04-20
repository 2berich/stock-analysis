package com.stock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author lei
 * @date 2019/4/20
 **/
@Configuration
@Component
public class StockConfig {

    /**
     * 手数
     */
    @Value("${stock.config.times: 3}")
    public Integer times;


    /**
     * 阀值
     */
    @Value("${stock.config.threshold: 4000}")
    public Integer threshold;

    /**
     * 涨幅限制
     */
    @Value("${stock.config.profitLimit: 5}")
    public Integer profitLimit;



}
