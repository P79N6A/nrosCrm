package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ReverseOrderDTO {

    @ApiModelProperty("退换货单id")
    private Long id;

    @ApiModelProperty(value = "退货单类型(0-整单退，1-部分退)")
    private Short reverseType;

    @ApiModelProperty(value = "退款类型(40.仅退款/ 39.线下退货退款(pos)/ 38.线上退货退款/37.线上换货)")
    private Short tradeType;

    @ApiModelProperty("退换货单号")
    private Long reverseOrderNo;

    @ApiModelProperty("订单号")
    private Long orderNo;

    @ApiModelProperty("应退货金额")
    private BigDecimal Amount;

    @ApiModelProperty("实际退款")
    private BigDecimal actualAmount;


    @ApiModelProperty("退换单详情列表")
    private List<ReverseOrderLineDTO> reverseOrderLineList = new ArrayList<ReverseOrderLineDTO>();

    @ApiModelProperty("退换货单状态(9.待审核 10.审核拒绝 11.审核通过 20.待退款 22.退款成功 23 买家发货 24.卖家收货 28.退货交易关闭)")
    private Short tradeStatus;

    @ApiModelProperty(value = "地址信息", hidden = true)
    private AddressDTO address;

    @ApiModelProperty("物流单号")
    private String shipmentNo;

    @ApiModelProperty("退换货原因")
    private Integer reverseCause;

    @ApiModelProperty(value = "退货单创建时间")
    protected Date gmtCreate;

    @ApiModelProperty(value = "退货单修改时间", hidden = true)
    protected Date gmtModified;

    @ApiModelProperty(value = "退货单来源")
    private Short reverseOrderSource;

    @ApiModelProperty(value = "退货单来源名称( 1.惠购 2.pos 3.直销 4.批发 )")
    private String reverseOrderSourceName;

    @ApiModelProperty(value = "店铺ID")
    private String shopCode;

    @ApiModelProperty(value = "买家id")
    private Long buyerId;

    @ApiModelProperty(value = "卖家id")
    private Long sellerId;

    @ApiModelProperty(value = "支付单号")
    private Long paymentNo;

    @ApiModelProperty(value = "应退劵")
    private BigDecimal payTicket;

    @ApiModelProperty(value = "退款时间")
    private Date paymentTime;

    @ApiModelProperty(value = "收款账户")
    private String paymentAmount;

    @ApiModelProperty(value = "操作人id")
    private String operatorId;

    @ApiModelProperty(value = "商家采购(商家店铺号)", hidden = true)
    private String buyerShopCode;

    @ApiModelProperty(value = "appId", hidden = true)
    private String appId;

    @ApiModelProperty("退货/退款状态")
    private Integer refundStatus;

}
