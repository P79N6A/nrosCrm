package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import java.io.Serializable;
import java.util.Date;

import com.ztesoft.zsmart.nros.common.model.BaseModel;

import lombok.Data;

/**
 * 优惠券领取记录
 * 
 * @author wangzhe
 * @date 2019/6/10 15:10
 */
@Data
public class CouponReceiveRecordsDTO extends BaseModel implements Serializable {
    /**
     * 优惠券领取记录-优惠券实例编码
     */
    private String instanceCode;

    /**
     * 优惠券领取记录-优惠券编码
     */
    private String couponCode;

    /**
     * 优惠券领取记录-发放人ID
     */
    private Long sendUserId;

    /**
     * 优惠券领取记录-发放人名称
     */
    private String sendUserName;

    /**
     * 优惠券领取记录-会员ID
     */
    private Long memberId;

    /**
     * 优惠券领取记录-会员名称
     */
    private String memberName;

    /**
     * 优惠券领取记录-优惠券类型:[1]满折券,[2]满减券,[3]满赠券,[4]满换券
     */
    private String couponType;

    /**
     * 优惠券领取记录-有效期开始时间
     */
    private Date useStartTime;

    /**
     * 优惠券领取记录-有效期截止时间
     */
    private Date useEndTime;

    /**
     * 优惠券领取记录-领取方式:[0]会员营销活动,[1]促销活动,[2]自领,[3]其他
     */
    private String receiveType;

    /**
     * 优惠券领取记录-领取时间
     */
    private Date receiveTime;

    /**
     * 优惠券领取记录-领取渠道
     */
    private String receiveChannel;

    /**
     * 优惠券领取记录-领券活动ID
     */
    private Long promotionId;

    /**
     * 优惠券领取记录-是否锁定
     */
    private String isLock;

    /**
     * 优惠券领取记录-微信核销门店编码
     */
    private Long storeId;

    /**
     * 优惠券领取记录-门店名称
     */
    private String storeName;

    /**
     * 优惠券领取记录-核销渠道
     */
    private String consumeChannel;

    /**
     * 优惠券领取记录-核销员ID
     */
    private Long consumeUserId;

    /**
     * 优惠券领取记录-自助核销时所需验证码
     */
    private String verifyCode;

    /**
     * 优惠券领取记录-是否核销
     */
    private String isUse;

    /**
     * 优惠券领取记录-核销时间
     */
    private Date useTime;

    /**
     * 优惠券领取记录-订单编码
     */
    private String orderCode;

    /**
     * 优惠券领取记录-规则ID
     */
    private Long ruleId;

    /**
     * 是否发送成功:[0]否,[1]是
     */
    private String sendSuccess;

    /**
     * 失败编码
     */
    private String errorCode;

    /**
     * 失败原因
     */
    private String errorMsg;

    /**
     * This field was generated by MyBatis Generator.
     */
    private static final long serialVersionUID = 1L;
}