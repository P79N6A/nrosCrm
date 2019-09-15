package com.ztesoft.zsmart.nros.crm.core.client.model.param;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhou.xiaofeng
 * @description
 * @date 2019-06-15
 */
@Data
@ApiModel("活动访问反馈对象")
public class CampaignFeedBackParam extends BaseModel implements Serializable {

    @ApiModelProperty(value = "反馈类型", required = true, example = "[1]点击,[2]报名,[3]-签到,[4]分享,[5]-停留时长,[6]邀请入会")
    private String feedbackType;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "客户移动设备", example = "iPhone X")
    private String device;

    @ApiModelProperty(value = "地理位置")
    private String location;

    @ApiModelProperty(value = "渠道ID")
    private Long channelId;

    @ApiModelProperty(value = "分享活动的会员ID", example = "10001")
    private Long shareMemberId;

    @ApiModelProperty(value = "推荐人ID", example = "10001")
    private Long inviterContactId;

    @ApiModelProperty(value = "导购员ID", example = "10001")
    private Long guideId;

    @ApiModelProperty(value = "微信openId", example = "10001")
    private String wxOpenId;

    @ApiModelProperty(value = "活动ID", required = true, example = "100001")
    private Long campaignId;

}
