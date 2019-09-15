package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

import java.util.Arrays;

/**
 * @description 积分变化Enum
 * @author wang.yulin01
 * @date 2019/4/15 15:08
 * @version V1.0
 **/
public enum PointsReasonEnum {

    /**
     * 商城
     */
    MALL("25", "商城"),

    /**
     * 云餐饮
     */
    REPAST("26", "云餐饮"),

    /**
     * 云POS
     */
    POS("27", "云POS"),

    /**
     * 微信小程序
     */
    APPLET("28", "微信小程序");

    private String name;

    private String state;

    PointsReasonEnum(String state, String name) {
        this.name = name;
        this.state = state;
    }

    private static String getState(String name) {
        for (PointsReasonEnum e : PointsReasonEnum.values()) {
            if (e.getName().equals(name)) {
                return e.state;
            }
        }
        return null;
    }

    /**
     * 功能描述
     * 
     * @param state
     * @return com.ztesoft.zsmart.nros.sbc.crm.cloud.member.enums.PointsReasonEnum
     * @author PQ
     * @date 2019/4/26
     */
    public static PointsReasonEnum getByState(String state) {
        return Arrays.stream(PointsReasonEnum.values()).filter(nodeEnum -> nodeEnum.state.equals(state)).findAny()
            .orElse(null);
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }
}
