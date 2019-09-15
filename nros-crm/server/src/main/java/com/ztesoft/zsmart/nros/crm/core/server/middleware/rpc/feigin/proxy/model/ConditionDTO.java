package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 规则条件
 * @author   wangzhe
 * @date     2019/6/2 23:56
 */
@Data
public class ConditionDTO extends BaseModel implements Serializable {
    /**
     * 规则条件-条件类型:[1]金额,[2]数量,[3]无条件
     */
    private String conditionType;

    /**
     * 规则条件-动作:[1]满,[2]第
     */
    private String action;

    /**
     * 规则条件-金额
     */
    private Long amountOfMoney;

    /**
     * 规则条件-数量
     */
    private Integer quantity;

    /**
     * 规则条件-优惠名称
     */
    private String discountName;

    /**
     * 规则条件-规则ID
     */
    private Long ruleId;

    /**
     * This field was generated by MyBatis Generator.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 活动优惠列表
     */
    private List<DiscountDTO> discountList;

    /**
     * 赠品列表
     */
    private List<GiftDTO> giftList;

    /**
     * 返券列表
     */
    private List<GiftCouponDTO> giftCouponList;

    /**
     * 换购商品列表
     */
    private List<PurchaseDTO> purchaseList;
}