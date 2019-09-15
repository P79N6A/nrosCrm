package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName OrderShipmentDTO
 * @description: TODO
 * @author: yangxh
 * @date: 2019/6/3
 * @Version:1.0
 */
@Data
public class OrderShipmentDTO extends BaseModel {

    @ApiModelProperty(value = "运单号")
    private String shipmentNo;

    @ApiModelProperty(value = "订单编号")
    private Long orderNo;

    @ApiModelProperty(value = "物流费用")
    private Long shipmentFee;

    @ApiModelProperty(value = "物流供应商")
    private Integer shipmentVendor;

    @ApiModelProperty(value = "物流单类型(1-订单 2- 退货单 3--换货单)")
    private Short preformType;

    @ApiModelProperty(value = "物流名称")
    private String shipmentName;

    @ApiModelProperty(value = "配送方式")
    private Integer shipmentType;

    @ApiModelProperty(value = "配送方式名称")
    private String shipmentTypeName;



}
