package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

/**
 * 保护期类型枚举类
 * 
 * @author PQ
 * @date 2019/4/26
 */
public enum ProtectDateTypeEnum {

    /**
     * 立即生效
     */
    RIGHTNOW("RIGHTNOW", "立即生效"),

    /**
     * 领取后指定天数生效
     */
    FIXTERM("FIXTERM", "领取后指定天数生效");

    private String value;

    private String name;

    ProtectDateTypeEnum(String value, String name) {
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
