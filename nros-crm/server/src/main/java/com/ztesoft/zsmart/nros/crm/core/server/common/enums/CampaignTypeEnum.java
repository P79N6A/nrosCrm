package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

/**
 * @author yangkai
 * @description 活动类型枚举类
 * @date
 */
public enum CampaignTypeEnum {
    /**
     * 报名活动
     */
    SIGN("报名活动", "1"),

    /**
     * 邀请有礼
     */
    INVITE("邀请有礼", "2");

    private String name;

    private String value;

    CampaignTypeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getNameByValue(String value) {
        for (CampaignTypeEnum c : CampaignTypeEnum.values()) {
            if (c.getValue().equals(value)) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
