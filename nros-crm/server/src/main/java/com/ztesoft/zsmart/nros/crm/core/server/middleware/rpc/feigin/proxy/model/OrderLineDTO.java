package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.alibaba.fastjson.JSONObject;
import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wufan
 * @title: OrderLineParam
 * @date 2019/6/2 11:06
 */
@Data
public class OrderLineDTO extends BaseModel {

    /**
     * tc_order_line-订单详情编号
     */
    private Long orderLineId;

    /**
     * tc_order_line-订单主表编号
     */
    private Long orderNo;

    @ApiModelProperty(value = "商品的sku")
    private Long skuId;


    @ApiModelProperty(value = "商品ID")
    private Long itemId;

    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @ApiModelProperty(value = "卖家ID(商家ID)")
    private Long sellerId;

    @ApiModelProperty(value = "买家ID")
    private Long buyerId;

    /**
     * tc_order_line-暂未使用
     */
    private Short orderLineStatus;

    @ApiModelProperty(value = "sku商品价格(会员匹配)")
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "实际价格")
    private BigDecimal actualPrice;

    @ApiModelProperty(value = "商品数量")
    private Integer skuQuantity;

    @ApiModelProperty(value = "总价格")
    private BigDecimal amount;

    @ApiModelProperty(value = "实际总价格")
    private BigDecimal actualAmount;

    @ApiModelProperty("value=商品品类ID")
    private Long classId;

    @ApiModelProperty(value = "商品是否支持退换货(0-支持,1-不支持)")
    private Short isSupportReverse;

    @ApiModelProperty(value = "打包属性")
    private Long packingUnit;

    /**
     * tc_order_line-商品扩展字段(商品价格，快照编号，标签等信息)
     */
    private JSONObject extItem;

    @ApiModelProperty(value = "店铺id")
    private String shopCode;

    @ApiModelProperty(value = "已收货数量")
    private Integer storageQuantity;

    @ApiModelProperty(value = "抵用劵")
    private BigDecimal payTicket;

    @ApiModelProperty(value = "换算小单位后的数量", hidden = true)
    private Integer skuUnitQuantity;

    @ApiModelProperty(value = "sku商品原价")
    private BigDecimal originPrice;


    @ApiModelProperty(value = "总抵用劵")
    private BigDecimal totalPayTicket;


//    @ApiModelProperty(hidden = true)
//    Map<String, PriceTemplate> skuPriceMap;
//
//    @ApiModelProperty(value = "商品",hidden = true)
//    Item item;
//    @ApiModelProperty(value = "sku对象",hidden = true)
//    Sku sku;


    @ApiModelProperty(value = "已退换货数量")
    private Integer retiredSkuQuantity;


}
