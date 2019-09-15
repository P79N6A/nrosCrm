package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ztesoft.zsmart.nros.common.model.BaseQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 有效的优惠券批次列表查询参数
 * @author yangshaoxin
 * @date 2019-04-09
 */
@Data
@ApiModel("有效的优惠券批次列表查询对象")
public class CouponDefineQuery extends BaseQuery implements Serializable  {

    @ApiModelProperty(value = "优惠券名称", required = false)
    private String couponName;

    @ApiModelProperty(value = "优惠券类型", example = "1:满折券,2:满减券,3:满赠券,4:满换券,7:运费券")
    private String couponType;

    @ApiModelProperty(value = "优惠券渠道列表")
    private List<String> channel;

    @ApiModelProperty(value = "支持门店列表")
    private List<String> storeCode;

    @ApiModelProperty(value = "优惠券状态", example = "0:设计中,1:启用,2:作废")
    private String couponStatus;

    /**
     * 优惠券编码
     */
    @ApiModelProperty(value = "优惠券编码")
    private String couponCode;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "会查出领取结束时间大于所传时间的优惠券")
    private Date startTime;

    private static final long serialVersionUID = 1L;

}
