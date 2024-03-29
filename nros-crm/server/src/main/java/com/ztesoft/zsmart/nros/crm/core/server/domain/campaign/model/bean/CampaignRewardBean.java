package com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.bean;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhou.xiaofeng
 * @description 活动奖励bean
 * @date 2019-06-10
 */
@Data
public class CampaignRewardBean extends BaseModel implements Serializable {
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

    /**
     * 活动奖励-活动ID
     */
    private Long campaignId;

    /**
     * This field was generated by MyBatis Generator.
     */
    private static final long serialVersionUID = 1L;

}
