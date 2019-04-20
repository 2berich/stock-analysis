package com.stock.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author lei
 * @date 2019/4/19
 **/
@Data
public class MainMonitorDetailPriceChangeVO{

    @JsonProperty("1")
    private String timeline;

    @JsonProperty("2525646")
    private Double profit;

}
