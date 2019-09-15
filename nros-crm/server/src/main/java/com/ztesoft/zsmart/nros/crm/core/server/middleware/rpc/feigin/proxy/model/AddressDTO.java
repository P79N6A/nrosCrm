package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wufan
 * @title: OrderLineParam
 * @date 2019/6/2 11:06
 */
@Data
public class AddressDTO {
    @ApiModelProperty(value = "国家id")
    private Long nationId;

    @ApiModelProperty(value = "国家code")
    private String nationCode;

    @ApiModelProperty(value = "省级id")
    private Long provinceId;

    @ApiModelProperty(value = "省级code")
    private String provinceCode;

    @ApiModelProperty(value = "市级id")
    private Long cityId;

    @ApiModelProperty(value = "市级code")
    private String cityCode;


    @ApiModelProperty(value = "区级id")
    private Long districtId;


    @ApiModelProperty(value = "区级code")
    private String districtCode;


    @ApiModelProperty(value = "详细地址")
    public String detail;


    @ApiModelProperty(value = "邮编地址")
    private String zipcode;


    @ApiModelProperty(value = "收货人")
    public String consignee;


    @ApiModelProperty(value = "手机号")
    private String phone;
}
