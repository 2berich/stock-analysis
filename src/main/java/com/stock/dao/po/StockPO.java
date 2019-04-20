package com.stock.dao.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lei
 * @date 2019/4/19
 **/
@Data
@ToString(callSuper = true)
@Entity
@Table(name = "tb_stock")
public class StockPO extends AbstractBaseEntity {

    /**
     * 股票代码
     */
    @Id
    private String id;

    @Column(name = "stock_code", length = 8)
    private String stockcode;

    @Column(name = "stock_name", length = 16)
    private String stockname;

    private Double price;

    /**
     * 涨幅
     */
    private Double profit;

    /**
     * 资金流出
     */
    @Column(name = "main_sell", length = 16)
    private String mainsell;


    /**
     * 资金流入
     */
    @Column(name = "main_buy", length = 16)
    private String mainbuy;

    /**
     * 资金净流入
     */
    @Column(length = 16)
    private String supv;

    @Column(name = "is_trade")
    private Boolean istrade;

    /**
     * 是否待选
     */
    private Boolean hot;
}
