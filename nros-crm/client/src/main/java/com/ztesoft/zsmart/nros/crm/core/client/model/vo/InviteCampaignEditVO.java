package com.ztesoft.zsmart.nros.crm.core.client.model.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动编辑VO
 * @date 2019-06-14
 */
@Data
public class InviteCampaignEditVO implements Serializable {
    /**
     * 活动-标识
     */
    private Long id;

    /**
     * 活动-活动名称
     */
    private String name;

    /**
     * 活动-是否长期活动
     */
    private String isLongTermActivity;

    /**
     * 活动-活动开始时间
     */
    private Date startTime;

    /**
     * 活动-活动结束时间
     */
    private Date endTime;

    /**
     * 活动-活动详情--活动规则
     */
    private String richDetail;

    /**
     * 活动奖励-是否选择邀请人优惠券奖励
     */
    private String isCompounInviter;

    /**
     * 活动奖励-邀请人奖励优惠券编码
     */
    private String compounCodeInviter;

    /**
     * 活动奖励-是否选择邀请人积分奖励
     */
    private String isPointInviter;

    /**
     * 活动奖励-邀请人奖励积分
     */
    private Integer pointInviter;

    /**
     * 活动奖励-邀请人奖励积分是否永久有效
     */
    private String pointIsPermanetInviter;

    /**
     * 活动奖励-邀请人积分奖励几日后生效
     */
    private Integer pointvalidAfterDaysInviter;

    /**
     * 活动奖励-是否选择邀请人其他奖励
     */
    private String isOtherInviter;

    /**
     * 活动奖励-邀请人其他奖励
     */
    private String otherInviter;

    /**
     * 活动奖励-是否选择被邀请人优惠券奖励
     */
    private String isCompounInvitee;

    /**
     * 活动奖励-被邀请人奖励优惠券编码
     */
    private String compounCodeInvitee;

    /**
     * 活动奖励-是否选择被邀请人积分奖励
     */
    private String isPointInvitee;

    /**
     * 活动奖励-被邀请人奖励积分
     */
    private Integer pointInvitee;

    /**
     * 活动奖励-被邀请人奖励积分是否永久有效
     */
    private String pointIsPermanetInvitee;

    /**
     * 活动奖励-被邀请人积分奖励几日后生效
     */
    private Integer pointvalidAfterDaysInvitee;

    /**
     * 活动奖励-是否选择被邀请人其他奖励
     */
    private String isOtherInvitee;

    /**
     * 活动奖励-被邀请人其他奖励
     */
    private String otherInvitee;

}
