package com.ztesoft.zsmart.nros.crm.core.client.model.dto;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动邀请明细DTO
 * @date 2019-04-13
 */
@Data
public class InviteDetailDTO extends BaseModel {

    /**
     * 活动服务明细-邀请人手机
     */
    private String invitorPhone;

    /**
     * 活动服务明细-注册人手机
     */
    private String registerPhone;

    /**
     * 活动服务明细-报名人手机
     */
    private String signPhone;

    /**
     * 活动服务明细-预约门店ID
     */
    private Long reservationStoreId;

    /**
     * 活动服务明细-是否新用户
     */
    private String isNew;

    /**
     * 活动服务明细-审核状态 0. 审核通过 1. 审核拒绝  2.取消预约
     */
    private String auditStatus;

    /**
     * 活动服务明细-是否已经签到
     */
    private String isSignIn;

    /**
     * 活动服务明细-签到码
     */
    private String signInCode;

    /**
     * 活动服务明细-报名时间
     */
    private String signTime;

    /**
     * 活动服务明细-注册时间
     */
    private Date registerTime;

    /**
     * 活动服务明细-奖励发放
     */
    private String isReward;

    /**
     * 活动服务明细-活动ID
     */
    private Long campaignId;
}
