/**
 * [Product]
 * crm
 * [Copyright]
 * Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 * MarketingProcessFinishStatusEnum.java
 * [History]
 * Version  Date      Author     Content
 * -------- --------- ---------- ------------------------
 * 1.0.0    2019/4/24   PQ         最初版本
 */
package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

/**
 * 营销流程执行是否完成状态枚举
 * 
 * @author PQ
 * @date 2019/4/24
 */
public enum MarketingProcessFinishStatusEnum {

    /**
     * 是
     */
    YES("是", "1"),

    /**
     * 否
     */
    NO("否", "0");

    private String name;

    private String value;

    MarketingProcessFinishStatusEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(String value) {
        for (MarketingProcessFinishStatusEnum c : MarketingProcessFinishStatusEnum.values()) {
            if (c.getValue().equals(value)) {
                return c.name;
            }
        }
        return null;
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
