package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询会员优惠券列表对象
 * @author yangshaoxin
 * @date 2019-04-09
 */
@Data
@ApiModel("查询会员优惠券列表对象")
public class CouponMemberIdQuery extends BaseQuery implements Serializable  {

    @ApiModelProperty(value = "会员ID", required = true)
    private Long memberId;

    @ApiModelProperty(value = "优惠券状态,不填默认返回所有", example = "0：可使用 1：已失效 2：已核销")
    private String status;

    @ApiModelProperty(value = "优惠券渠道", example = "渠道标识")
    private List<String> channel;

    @ApiModelProperty(value = "查询领取开始时间", example = "yyyy-MM-dd HH:mm:ss")
    private String receiveStartTime;

    @ApiModelProperty(value = "查询领取结束时间", example = "yyyy-MM-dd HH:mm:ss")
    private String receiveEndTime;

    @ApiModelProperty(value = "查询使用开始时间", example = "yyyy-MM-dd HH:mm:ss")
    private String usedStartTime;

    @ApiModelProperty(value = "查询使用结束时间", example = "yyyy-MM-dd HH:mm:ss")
    private String usedEndTime;

    private static final long serialVersionUID = 1L;

}
