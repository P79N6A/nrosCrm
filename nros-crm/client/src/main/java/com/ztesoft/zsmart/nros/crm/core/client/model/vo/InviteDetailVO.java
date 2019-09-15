package com.ztesoft.zsmart.nros.crm.core.client.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhou.xiaofeng
 * @description 邀请明细详情VO
 * @date 2019-06-05
 */
@Data
public class InviteDetailVO implements Serializable {

    private static final long serialVersionUID = 6651225112282936678L;
    /**
     * 活动服务明细-标识
     */
    private Long id;

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

    /**
     * 活动服务明细-商家ID
     */
    private Long merchantId;

    /**
     * 活动服务明细-创建时间
     */
    private Date gmtCreate;

    /**
     * 活动服务明细-修改时间
     */
    private Date gmtModified;

    /**
     * 活动服务明细-创建人
     */
    private Long creator;

    /**
     * 活动服务明细-修改人
     */
    private Long modifier;

    /**
     * 活动服务明细-状态
     */
    private String status;

}
