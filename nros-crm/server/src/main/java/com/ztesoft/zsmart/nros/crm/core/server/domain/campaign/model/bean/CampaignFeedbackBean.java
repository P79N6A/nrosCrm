package com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.bean;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhou.xiaofeng
 * @description 活动反馈bean
 * @date 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CampaignFeedbackBean extends BaseModel implements Serializable {
    /**
     * 活动反馈-反馈类型：1-点击、2-报名、3-签到、4-分享、5-停留时长、6-邀请入会
     */
    private String feedbackType;

    /**
     * 活动反馈-开始时间
     */
    private Date startTime;

    /**
     * 活动反馈-结束时间
     */
    private Date endTime;

    /**
     * 活动反馈-客户移动设备（提交表单的用户收集此信息）
     */
    private String device;

    /**
     * 活动反馈-地理位置（提交表单的用户收集此信息）
     */
    private String location;

    /**
     * 活动反馈-渠道ID
     */
    private Long channelId;

    /**
     * 活动反馈-分享活动的会员ID
     */
    private Long shareMemberId;

    /**
     * 活动反馈-推荐人ID
     */
    private Long inviterContactId;

    /**
     * 活动反馈-导购员ID
     */
    private Long guideId;

    /**
     * 活动反馈-微信openId
     */
    private String wxOpenId;

    /**
     * 活动反馈-活动ID
     */
    private Long campaignId;

}
