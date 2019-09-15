package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

/**
 * 优惠券使用状态枚举类
 * 
 * @author yangshaoxin
 * @date 2019/4/10 20:28
 */
public enum YesOrNoEnum {

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

    YesOrNoEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(String value) {
        for (YesOrNoEnum c : YesOrNoEnum.values()) {
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
