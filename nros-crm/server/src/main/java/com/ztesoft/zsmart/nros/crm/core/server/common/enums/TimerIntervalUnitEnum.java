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
 * 定时器“等待时间单位"枚举
 * 
 * @author PQ
 * @date 2019/6/5
 */
public enum TimerIntervalUnitEnum {

    /**
     * 立即执行
     */
    NOW(1, "S", "立即执行"),

    /**
     * 分
     */
    MINUTES(2, "M", "分"),

    /**
     * 天
     */
    DAY(4, "D", "天"),

    /**
     * 周
     */
    WEEK(5, "W", "周"),

    /**
     * 月
     */
    MONTH(6, "M", "月");

    private Integer value;

    private String code;

    private String name;

    TimerIntervalUnitEnum(Integer value, String code, String name) {
        this.value = value;
        this.code = code;
        this.name = name;
    }

    public static TimerIntervalUnitEnum getByValue(Integer value) {
        for (TimerIntervalUnitEnum c : TimerIntervalUnitEnum.values()) {
            if (c.getValue().equals(value)) {
                return c;
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

    public Integer getValue() {
        return value;
    }

    /*public void setValue(Integer value) {
        this.value = value;
    }*/

    public String getCode() {
        return code;
    }

    /*public void setCode(String code) {
        this.code = code;
    }*/
}
