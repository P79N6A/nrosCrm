package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yangshaoxin
 * @version 1.0
 * @Description 优惠券领取列表查询参数
 * @date 2019/4/17 17:41
 */
@Data
@ApiModel("优惠券领取列表查询对象")
public class CouponReceiveListQuery extends BaseQuery implements Serializable {

    @ApiModelProperty(value = "优惠券编码", required = true)
    private String couponCode;

    @ApiModelProperty(value = "领取方式")
    private String receiveType;

    @ApiModelProperty(value = "查询领取开始时间", example = "yyyy-MM-dd HH:mm:ss")
    private String receiveStartTime;

    @ApiModelProperty(value = "查询领取结束时间", example = "yyyy-MM-dd HH:mm:ss")
    private String receiveEndTime;

    @ApiModelProperty(value = "查询使用开始时间", example = "yyyy-MM-dd HH:mm:ss")
    private String usedStartTime;

    @ApiModelProperty(value = "查询使用结束时间", example = "yyyy-MM-dd HH:mm:ss")
    private String usedEndTime;

    @ApiModelProperty(value = "领取导购ID")
    private Long sendUserId;

    @ApiModelProperty(value = "是否核销", example = "1.已核销 0.未核销")
    private String isUse;

    private static final long serialVersionUID = 1L;


}
