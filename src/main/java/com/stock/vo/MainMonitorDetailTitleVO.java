package com.stock.vo;

import lombok.Data;

/**
 * @author lei
 * @date 2019/4/19
 **/
@Data
public class MainMonitorDetailTitleVO {

    private String stockcode;

    private String stockname;

    private Double price;

    private String profit;

    private String mailsell;

    private String mainbuy;

    private Boolean istrade;
}
