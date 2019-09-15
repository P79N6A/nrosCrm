package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

/**
 * 营销类型枚举
 * 
 * @author PQ
 * @date 2019/4/18
 */
public enum MarketingTypeEnum {

    /**
     * 主动营销
     */
    PUSH("1", "主动营销"),

    /**
     * 事件营销
     */
    EVENT("2", "事件营销");

    private String code;

    private String name;

    MarketingTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

}
