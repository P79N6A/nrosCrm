package com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model;

import lombok.Data;

/**
 * @author yangkai
 * @description 活动服务类BO
 * @date 2019-06-18
 */
@Data
public class CampaignServiceBO {
    /**
     * 报名姓名
     */
    private String signName;

    /**
     * 报名电话
     */
    private String signPhone;

    /**
     * 活动id
     */
    private Long campaignId;

    /**
     * 签到码
     */
    private String signInCode;

    /**
     * 是否已签到
     */
    private String isSignIn;

    /**
     * 预约门店id
     */
    private Long reservationStoreId;

    /**
     * 预约时间段id
     */
    private Long reservationTimeId;

    /**
     * 报名时间
     */
    private String signTime;

    /**
     * 签到时间
     */
    private String signInTime;
}
