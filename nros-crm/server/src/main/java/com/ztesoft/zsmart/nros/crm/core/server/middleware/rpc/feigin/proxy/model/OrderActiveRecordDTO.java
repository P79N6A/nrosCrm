package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单活动记录
 * @author wangzhe
 * @date 2019-07-05
 */
@Data
public class OrderActiveRecordDTO {
    /**
     * 活动记录-活动名称
     */
    private String activeName;

    /**
     * 活动记录-活动类型:[1]单品类.[2]订单类,[3]运费类,[4]优惠券
     */
    private String activeType;

    /**
     * 活动类型名称
     */
    private String activeTypeName;

    /**
     * 活动记录-订单编码
     */
    private Long orderNo;

    /**
     * 活动记录-总优惠金额
     */
    private BigDecimal totalDiscountAmt;

    /**
     * 活动记录-门店编码
     */
    private String storeCode;

    /**
     * 活动记录-渠道编码
     */
    private String channelCode;

    /**
     * 活动记录-会员ID
     */
    private Long memberId;

}
