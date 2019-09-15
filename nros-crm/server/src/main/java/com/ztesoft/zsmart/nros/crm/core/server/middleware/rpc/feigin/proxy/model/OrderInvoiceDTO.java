package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * tc_order_invoice 发票
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-06-13
 */
@Data
public class OrderInvoiceDTO extends BaseModel {
    /**
     * tc_order_invoice 发票-invoice_id
     */
    private Long invoiceId;

    /**
     * tc_order_invoice 发票-order_no
     */
    private Long orderNo;

    /**
     * tc_order_invoice 发票-seller_id
     */
    private Long sellerId;

    /**
     * tc_order_invoice 发票-备注信息
     */
    private String remark;

    /**
     * tc_order_invoice 发票-0--未开票  1-- 已经开票
     */
    private Short invoiceStatus;

    /**
     * tc_order_invoice 发票-发票抬头
     */
    private String invoiceTitle;

    /**
     * tc_order_invoice 发票-抬头内容
     */
    private String invoiceContent;

    /**
     * tc_order_invoice 发票-发票金额
     */
    private Long invoiceAmount;

    /**
     * tc_order_invoice 发票-税号
     */
    private String invoiceTaxNo;

    /**
     * tc_order_invoice 发票-发票类型
     */
    private Integer invoiceType;

    private String invoiceTypeName;
    /**
     * tc_order_invoice 发票-开票税金
     */
    private Long invoiceTax;

    /**
     * tc_order_invoice 发票-公司名称
     */
    private String vatCompanyName;

    /**
     * tc_order_invoice 发票-公司地址
     */
    private String vatCompanyAddress;

    /**
     * tc_order_invoice 发票-联系电话
     */
    private String vatTelephone;

    /**
     * tc_order_invoice 发票-开户银行
     */
    private String vatBankName;

    /**
     * tc_order_invoice 发票-银行帐号
     */
    private String vatBankAccount;

    /**
     * tc_order_invoice 发票-发票代码
     */
    private String invoiceCode;

    /**
     * tc_order_invoice 发票-开票日期
     */
    private Date invoiceDate;
}
