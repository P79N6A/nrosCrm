package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReverseOrderLineDTO {

    @ApiModelProperty(value = "退换货单号")
    private Long reverseOrderNo;

    @ApiModelProperty(value = "商品的sku")
    private Long skuId;

    @ApiModelProperty(value = "商品数量")
    private BigDecimal skuQuantity;

    @ApiModelProperty(value = "sku商品价格")
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "实际sku价格")
    private BigDecimal actualPrice;

    @ApiModelProperty(value = "总价格")
    private BigDecimal amount;

    @ApiModelProperty(value = "实际总价格")
    private BigDecimal actualAmount;

    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @ApiModelProperty(value = "商品ID")
    private Long itemId;

    @ApiModelProperty(value = "卖家ID(商家ID)")
    private Long sellerId;

    @ApiModelProperty(value = "买家ID")
    private Long buyerId;

    @ApiModelProperty(value = "应退劵")
    private BigDecimal payTicket;

    @ApiModelProperty(value = "总退劵")
    private BigDecimal totalPayTicket;

    @ApiModelProperty(value = "打包属性")
    private Long packingUnit;

    @ApiModelProperty("value=商品品类ID")
    private Long classId;

    /**
     * 根据装箱单位后换算后的数量
     */
    @ApiModelProperty(value = "换算单位的数量", hidden = true)
    private BigDecimal skuUnitQuantity;


}
