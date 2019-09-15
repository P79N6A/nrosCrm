package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

/**
 * 营销流程定义-触发频次类型枚举类
 * 
 * @author PQ
 * @date 2019/4/26
 */
public enum MarketingFrequenceTypeEnum {
    /**
     * 不限制
     */
    NOLIMIT("0", "不限制"),

    /**
     * 每个客户仅能触发一次
     */
    ONLYONE("1", "每个客户仅能触发一次"),

    /**
     * 每个客户在一段时间内仅触发一次
     */
    ONEINRANGE("2", "每个客户在一段时间内仅触发一次");
    private String value;

    private String name;

    MarketingFrequenceTypeEnum(String value, String name) {
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
