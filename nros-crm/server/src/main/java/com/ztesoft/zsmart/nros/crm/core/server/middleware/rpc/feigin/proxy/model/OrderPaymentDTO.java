package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * tc_order_payment订单支付
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-06-29
 */
@Data
public class OrderPaymentDTO extends BaseModel {
    /**
     * tc_order_payment订单支付-订单号
     */
    private Long orderNo;

    /**
     * tc_order_payment订单支付-1--正向订单 2 -- 逆向订单
     */
    private Long paymentType;

    private String paymentTypeName;

    /**
     * tc_order_payment订单支付-支付单号
     */
    private String paymentNo;

    /**
     * tc_order_payment订单支付-支付通道 微信：wechat;支付宝：alipay;现金通道：CASH;银联POS：UNIONPAYPOS;会员余额: ;同仁堂一卡通：TRTCARD
     */
    private String paymentVendor;

    private String paymentVendorName;
    /**
     * tc_order_payment订单支付-支付金额
     */
    private BigDecimal paymentAmount;

    /**
     * tc_order_payment订单支付-支付账号
     */
    private String paymentAccount;

    /**
     * OrderPaymentDO-交易流水（由支付中心给出）
     */
    private String serialNumber;

    /**
     * This field was generated by MyBatis Generator.
     */
    private static final long serialVersionUID = 1L;
}
