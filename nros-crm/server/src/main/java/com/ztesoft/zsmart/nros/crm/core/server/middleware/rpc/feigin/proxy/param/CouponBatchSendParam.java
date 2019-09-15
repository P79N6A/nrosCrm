package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import java.io.Serializable;
import java.util.List;

import com.ztesoft.zsmart.nros.common.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 批量发送优惠券参数
 * 
 * @author yangshaoxin
 * @version 1.0
 * @date 2019/06/15 15:18
 */
@Data
@ApiModel("批量发送优惠券参数对象")
public class CouponBatchSendParam extends BaseModel implements Serializable {

    @ApiModelProperty(value = "优惠券编码", required = true)
    private String couponCode;

    @ApiModelProperty(value = "领取方式", example = "0.会员营销活动,1.促销活动,2.自领,3.其他", required = true)
    private String receiveType;

    @ApiModelProperty(value = "领取渠道")
    private String receiveChannel;

    @ApiModelProperty(value = "活动编码")
    private String activeCode;

    @ApiModelProperty(value = "活动实例编码")
    private String activeInstanceCode;

    @ApiModelProperty(value = "活动节点编码")
    private String activeNodeCode;

    @ApiModelProperty(value = "会员ID名称列表", required = true)
    private List<MemberIdNameParam> memberIdNameList;
}
