package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums;

import java.util.Arrays;

/**
 * 营销流程执行节点类型枚举
 *
 * @author PQ
 * @date 2019/07/24
 */
public enum MarketingNodeExecuteTypeEnum {
    FILTER("FILTER", "筛选客群", false), SENDSMS("SENDSMS", "发送短信", true), SENDPOINT("SENDPOINT", "送积分", true), SENDCOUPON(
        "SENDCOUPON", "送优惠券", true), ADDTAG("ADDTAG", "打标签", true), COUPON_CANCEL("COUPON_CANCEL", "优惠券核销",
            false), COUPON_GET("COUPON_GET", "优惠券领取", false), JUDGE_Y("JUDGE_Y", "判断Y", false), JUDGE_N("JUDGE_N",
                "判断N", false), GROUP_MASTER("GROUP_MASTER", "分组", false), GROUP_BRANCH("GROUP_BRANCH", "分组明细", false);

    private String code;

    private String name;

    // 是否用于前台展示
    private boolean display;

    MarketingNodeExecuteTypeEnum(String code, String name, boolean display) {
        this.code = code;
        this.name = name;
        this.display = display;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public boolean getDisplay() {
        return this.display;
    }

    public static MarketingNodeExecuteTypeEnum getByCode(String code) {
        return Arrays.stream(MarketingNodeExecuteTypeEnum.values()).filter(typeEnum -> typeEnum.getCode().equals(code))
            .findAny().orElse(null);
    }
}
