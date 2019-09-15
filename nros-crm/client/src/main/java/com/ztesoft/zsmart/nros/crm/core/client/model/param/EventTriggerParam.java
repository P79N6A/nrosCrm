package com.ztesoft.zsmart.nros.crm.core.client.model.param;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 事件触发传入参数
 * @author PQ
 * @date 2019/4/18
*/
@Data
@ApiModel("事件触发参数对象")
public class EventTriggerParam extends BaseModel implements Serializable {

    /**
     * 事件触发-event_id
     */
    private Long eventId;

    @ApiModelProperty(value = "触发会员ID", required = true)
    private Long memberId;

    @ApiModelProperty(value = "触发会员姓名", required = true)
    private String memberName;

    @ApiModelProperty(value = "触发会员手机号", required = true)
    private String memberPhone;

    @ApiModelProperty(value = "触发会员微信昵称", required = false)
    private String wxNickname;

    @ApiModelProperty(value = "触发识别ID(如：注册事件的渠道ID，消费事件的订单ID等)", required = true)
    private Long identifyId;

    @ApiModelProperty(value = "触发识别编码（和identifyId作用类似，用作后续扩展）", required = false)
    private String identifyCode;


    private static final long serialVersionUID = 1L;
}
