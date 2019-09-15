package com.ztesoft.zsmart.nros.crm.core.client.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author yangkai
 * @description 活动详情DTO
 * @date 2019-06-18
 */
@Data
public class CampaignServiceDTO {
    /**
     * 营销活动服务明细-邀请人手机
     */
    private String invitorPhone;

    /**
     * 营销活动服务明细-注册人手机
     */
    private String registerPhone;

    /**
     * 营销活动服务明细-报名人手机
     */
    private String signPhone;

    /**
     * 营销活动服务明细-预约门店ID
     */
    private Long reservationStoreId;

    /**
     * 营销活动服务明细-是否新用户
     */
    private String isNew;

    /**
     * 营销活动服务明细-审核状态 0. 审核通过 1. 审核拒绝  2.取消预约
     */
    private String auditStatus;

    /**
     * 营销活动服务明细-是否已经签到
     */
    private String isSignIn;

    /**
     * 营销活动服务明细-签到码
     */
    private String signInCode;

    /**
     * 营销活动服务明细-签到时间
     */
    private String signInTime;

    /**
     * 营销活动服务明细-报名时间
     */
    private String signTime;

    /**
     * 营销活动服务明细-注册时间
     */
    private Date registerTime;

    /**
     * 营销活动服务明细-奖励发放
     */
    private String isReward;

    /**
     * 营销活动服务明细-预约时间段id
     */
    private Long reserveTimeId;

    /**
     * 营销活动服务明细-活动ID
     */
    private Long campaignId;

    /**
     * 营销活动服务明细-签到用户姓名
     */
    private String signName;
}
