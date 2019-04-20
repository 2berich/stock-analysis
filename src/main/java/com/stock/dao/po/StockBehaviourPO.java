package com.stock.dao.po;

import com.stock.enums.TradeNatureEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author lei
 * @date 2019/4/19
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_stock_behaviour")
public class StockBehaviourPO extends AbstractBaseEntity {

    /**
     * stockCode-date-time
     */
    @Id
    private String id;

    @Column(name = "stock_code", length = 16)
    private String stockcode;

    @Column(name = "stock_name", length = 16)
    private String stockname;

    @Column(length = 16)
    private String nature;

    /**
     * 成交手数
     */
    private Integer volume;

    /**
     * 1 主力买；2；主力卖
     */
    @Column(name = "trade_type")
    private Integer tradetype;

    /**
     * 1 主力买；2；主力卖
     */
    @Column(name = "trade_nature")
    @Enumerated(EnumType.STRING)
    private TradeNatureEnum tradeNature;

    /**
     * 平均价
     */
    private Double avgprice;

    /**
     * 金额
     */
    @Column(length = 16)
    private String value;

    /**
     * 金额数字
     */
    private Long money;

    /**
     * 时间
     */
    @Column(length = 32)
    private String ctime;

    /**
     * 时间
     */
    @Column(length = 32)
    private String otime;
}
