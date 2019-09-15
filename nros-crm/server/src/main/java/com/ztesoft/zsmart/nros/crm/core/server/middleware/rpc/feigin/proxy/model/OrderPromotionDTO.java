package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yuanxiaokai
 * @date 2019/8/8
 */
@Data
public class OrderPromotionDTO {

    /**
     * OrderPromotionDO-订单商品ID
     */
    private Long orderLineId;

    /**
     * OrderPromotionDO-主订单编号
     */
    private Long orderNo;

    /**
     * OrderDO-"OMO商城渠道", 1  "新分销渠道", 2  "云餐饮", 4
     */
    private String channelId;

    /**
     * OrderPromotionDO-门店id
     */
    private String storeCode;

    /**
     * OrderPromotionDO-商品SKU编码
     */
    private String skuCode;

    /**
     * OrderPromotionDO-优惠金额
     */
    private BigDecimal discountAmt;

    /**
     * OrderPromotionDO-活动类型
     */
    private String activeType;
    private String activeTypeName;
    /**
     * OrderPromotionDO-活动名称
     */
    private String activeName;

    /**
     * OrderPromotionDO-活动编码
     */
    private String activeCode;

    /**
     * OrderPromotionDO-优惠名称
     */
    private String discountName;

    /**
     * OrderPromotionDO-优惠数量(销售单位数量)
     */
    private BigDecimal discountQuantity;

    /**
     * OrderPromotionDO-商品原始金额
     */
    private BigDecimal originalAmt;

    /**
     * OrderPromotionDO-商品原sku价格
     */
    private BigDecimal originalSkuPrice;

    /**
     * 销售单位
     */
    private Long unitId;

    /**
     * OrderPromotionDO-优惠类型:[1]满折,[2]满减,[3]满赠,[4]满换,[5]满返,[7]运费.[8]单品特价;
     */
    private String discountType;

    /**
     * OrderPromotionDO-根订单编号
     */
    private Long rootOrderNo;

    /**
     * 优惠券实例编码
     */
    private String couponInstanceCode;
}
