package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

/**
 * 营销流程定义-频次单位枚举类
 * 
 * @author PQ
 * @date 2019/4/26
 */
public enum MarketingFrequenceUnitEnum {

    /**
     * 天
     */
    DAY("0", "天"),

    /**
     * 周
     */
    WEEK("1", "周"),
    /**
     * 月
     */
    MONTH("2", "月");
    private String value;

    private String name;

    MarketingFrequenceUnitEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/

    public String getValue() {
        return value;
    }

    /*public void setValue(String value) {
        this.value = value;
    }*/

}
