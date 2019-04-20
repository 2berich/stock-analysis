package com.stock.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author lei
 * @date 2019/4/19
 **/
@Data
public class MainMonitorDetailBehaviourVO {

    private String nature;

    private String volume;

    private Integer tradetype;

    private Double avgprice;

    private String value;

    private Long money;

    private String ctime;

    private String otime;
}
