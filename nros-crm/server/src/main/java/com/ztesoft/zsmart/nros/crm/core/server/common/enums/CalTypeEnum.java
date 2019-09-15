package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

/**
 * @description 积分增减类型枚举
 * @author wang.yulin01
 * @date 2019/4/15 15:03
 * @version V1.0
 **/
public enum CalTypeEnum {
    /**
     * 增加
     */
    ADD("add", "增加"),
    /**
     * 减少
     */
    SUB("sub", "减少");

    private String name;

    private String state;

    CalTypeEnum(String state, String name) {
        this.name = name;
        this.state = state;
    }

    private static String getState(String name) {
        for (CalTypeEnum e : CalTypeEnum.values()) {
            if (e.getName().equals(name)) {
                return e.state;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }
}
