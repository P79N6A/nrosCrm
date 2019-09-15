package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch.BaseJudgeNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch.CouponCancelJudgeNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch.CouponGetJudgeNodeHandler;

/**
 * 控制节点类型
 * 
 * @author PQ
 * @date 2019/6/27
 */
public enum MarketingJudgeNodeTypeEnum {

    // 优惠券领取
    COUPON_GET(1, CouponGetJudgeNodeHandler.class),
    // 优惠券核销
    COUPON_CANCEL(2, CouponCancelJudgeNodeHandler.class);
    //// 活动报名打开
    // ACTIVITY_OPEN(20, ActivityOpenJudgeFlowNode.class),
    //// 活动报名报名
    // ACTIVITY_APPLY(21, ActivityApplyJudgeFlowNode.class),
    //// 活动报名签到
    // ACTIVITY_SIGN_IN(22, ActivitySignInJudgeFlowNode.class),
    //// 图文消息打开
    // IMAGE_TEXT_MSG_OPEN(30, ImageTextMsgOpenJudgeFlowNode.class),
    //// 邀请有礼打开
    // INVITE_OPEN(40, InviteOpenJudgeFlowNode.class),
    //// 邀请有礼邀请好友
    // INVITE_FRIENDS(41, InviteFriendsJudgeFlowNode.class);

    MarketingJudgeNodeTypeEnum(Integer conditionType, Class<? extends BaseJudgeNodeHandler> className) {
        this.conditionType = conditionType;
        this.className = className;
    }

    /**
     * 行为类型（控制节点的类型）
     */
    private Integer conditionType;

    private Class<? extends BaseJudgeNodeHandler> className;

    public Integer getConditionType() {
        return conditionType;
    }

    public Class getClassName() {
        return className;
    }

    public static MarketingJudgeNodeTypeEnum index(Integer conditionType) throws BusiException {
        MarketingJudgeNodeTypeEnum[] marketingJudgeNodeTypeEnums = MarketingJudgeNodeTypeEnum.values();
        for (MarketingJudgeNodeTypeEnum item : marketingJudgeNodeTypeEnums) {
            if (item.getConditionType().equals(conditionType)) {
                return item;
            }
        }
        throw new BusiException("控制节点类型异常");
    }
}
