package com.ztesoft.zsmart.nros.crm.core.client.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动奖励发放参数
 * @date 2019-06-10
 */
@Data
@ApiModel("邀请活动奖励发放参数")
public class InviteRewardParam {

    @ApiModelProperty(value = "活动ID")
    private Long activityId;

    @ApiModelProperty(value = "新会员ID")
    private Long newMemberId;

    @ApiModelProperty(value = "邀请人ID")
    private Long inviterId;

}
