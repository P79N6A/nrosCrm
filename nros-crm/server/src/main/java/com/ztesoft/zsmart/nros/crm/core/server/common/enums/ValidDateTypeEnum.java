package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

/**
 * 有效期类型枚举类
 * 
 * @author PQ
 * @date 2019/4/26
 */
public enum ValidDateTypeEnum {

    /**
     * 永久有效
     */
    LASTED("LASTED", "永久有效"),

    /**
     * 领取后多少天到期
     */
    FIXTERM("FIXTERM", "领取后多少天到期"),

    /**
     * 截至到特定日期
     */
    INVALIDTIME("INVALIDTIME", "截至到特定日期");

    private String value;

    private String name;

    ValidDateTypeEnum(String value, String name) {
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
