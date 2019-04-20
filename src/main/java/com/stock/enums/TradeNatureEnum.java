package com.stock.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author lei
 * @date 2019/4/20
 **/
public enum TradeNatureEnum {

    ACTIVE_BUY("主力主买"),
    PASSIVE_BUY("主力被买"),
    ACTIVE_SELL("主力主卖"),
    PASSIVE_SELL("主力被卖");

    private String name;

    TradeNatureEnum(String name) {
        this.name = name;
    }

    public static TradeNatureEnum resolveOf(String name) {
        for (TradeNatureEnum type : TradeNatureEnum.values()) {
            if (StringUtils.equals(name, type.getName())) {
                return type;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
