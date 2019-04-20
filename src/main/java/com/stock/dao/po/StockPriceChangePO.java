package com.stock.dao.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_stock_price_change")
public class StockPriceChangePO extends AbstractBaseEntity {

    /**
     * 即timeline
     */
    @Id
    private String id;

    @Column(name = "stock_code", length = 16)
    private String stockcode;

    @Column(name = "stock_name", length = 16)
    private String stockname;

    @Column(name = "timeline", length = 16)
    private String timeline;

    private Double profit;

}
