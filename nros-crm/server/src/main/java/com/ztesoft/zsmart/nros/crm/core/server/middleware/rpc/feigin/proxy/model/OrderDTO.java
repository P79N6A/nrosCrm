package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.alibaba.fastjson.JSONObject;
import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单
 *
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-06-13
 */
@Data
public class OrderDTO extends BaseModel {

    @ApiModelProperty(value = "自提信息", hidden = true)
    private OrderSinceDTO orderSince;

    /**
     * OrderDO-根订单号
     */
    @ApiModelProperty(value = "根订单编号", hidden = true)
    private Long rootOrderNo;

    /**
     * OrderDO-父订单号
     */
    @ApiModelProperty(value = "父订单编号", hidden = true)
    private Long parentOrderNo;

    /**
     * OrderDO-所属主订单编号,
     */
    @ApiModelProperty(value = "订单编号", hidden = true)
    private Long orderNo;

    /**
     * OrderDO-外部导入订单的原始编号
     */
    @ApiModelProperty(value = "外部流水")
    private String orderIdOut;

    /**
     * OrderDO-卖家标识
     */
    private Long sellerId;

    /**
     * OrderDO-会员号， 来自于会员中心
     */
    @ApiModelProperty(value = "会员号")
    private Long memberCardId;

    /**
     * OrderDO-买家店铺号(适用于商家采购)
     */
    @ApiModelProperty(value = "买家店铺号", hidden = true)
    private String buyerShopCode;

    /**
     * OrderDO-买家标识
     */
    private Long buyerId;

    /**
     * OrderDO-买家备注信息
     */
    @ApiModelProperty(value = "买家备注")
    private String comment;

    @ApiModelProperty(value = "卖家备注")
    private String remark;

    /**
     * OrderDO-交易类型, 即付交易、预售、商城交易、
     */
    @ApiModelProperty(value = "订单类型(5.pos终端零售  6.惠购零售购买  7.商家批发采买 8.拼团交易  9.预售交易)")
    private Short tradeType;
    @ApiModelProperty(value = "订单类型(pos终端零售  惠购零售购买  商家批发采买 拼团交易 预售交易)")
    private String tradeTypeName;
    /**
     * OrderDO-内部订单类型， 逻辑聚合单、物理订单、物流前置单
     */
    @ApiModelProperty(value = "内部订单类型， 1.逻辑聚合单、2.实际物理单", hidden = true)
    private Short orderType;
    @ApiModelProperty(value = "内部订单类型， 1.逻辑聚合单、2.实际物理单", hidden = true)
    private String orderTypeName;

    /**
     * OrderDO-交易状态， 待支付、已支付、 待发货、已关闭等
     */
    @ApiModelProperty(value = "订单状态(1.订单初始化  2.订单待支付 3.完成支付 4.完成阶段支付 5.完成订单派发 6.接受订单 7.待收货 8.已收货 -1.交易异常关闭 -2.交易超时关闭 -3.交易正常取消 0.交易成功)", hidden = true)
    private Short tradeStatus;

    @ApiModelProperty(value = "订单状态(1.订单初始化  2.订单待支付 3.完成支付 4.完成阶段支付 5.完成订单派发 6.接受订单 7.待收货 8.已收货 -1.交易异常关闭 -2.交易超时关闭 -3.交易正常取消 0.交易成功)", hidden = true)
    private String tradeStatusName;
    /**
     * OrderDO-订单业务来源，商城、惠购、pos等
     */
    @ApiModelProperty(value = "订单来源( 1.惠购 2.pos 3.直销 4.批发 )")
    private Short orderSource;

    @ApiModelProperty(value = "订单来源( 1.惠购 2.pos 3.直销 4.批发 )")
    private String orderSourceName;

    /**
     * OrderDO-支付渠道(暂未使用)
     */
    @ApiModelProperty(value = "支付渠道", hidden = true)
    private Short paymentOption;

    /**
     * OrderDO-0-未支付，1-已支付
     */
    @ApiModelProperty(value = "订单是否已支付(0-未支付，1-已支付)")
    private Short payStatus;

    /**
     * OrderDO-支付单号
     */
    @ApiModelProperty(value = "支付单号")
    private String paymentNo;

    /**
     * OrderDO-支付时间
     */
    @ApiModelProperty(value = "支付时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paymentTime;

    /**
     * OrderDO-商品实际总金额
     */
    @ApiModelProperty(value = "订单总金额", notes = "营销计算中必须传入传入")
    private BigDecimal amount;

    /**
     * OrderDO-物流费用
     */
    @ApiModelProperty(value = "物流费用")
    private BigDecimal shipmentFee;

    /**
     * OrderDO-实际支付金额(优惠后)
     */
    @ApiModelProperty(value = "实际金额")
    private BigDecimal actualAmount;


    /**
     * OrderDO-是否拆单(0-未拆单，1-已拆单)
     */
    @ApiModelProperty(value = "是否拆单0-未拆，1-已拆", hidden = true)
    private Short orderStatus;

    /**
     * OrderDO-订单是否支持退换货(0.支持 ，1不支持)
     */
    @ApiModelProperty(value = "订单是否支持退换货(0-支持,1-不支持)", hidden = true)
    private Short orderSupportReverse;

    /**
     * OrderDO-是否参加订单型营销(0-未参加，1-参加)
     */
    @ApiModelProperty(value = "订单是否参加订单型营销(0-未参加，1-已参加)")
    private Short orderPromotion;

    /**
     * OrderDO-店铺id
     */
    @ApiModelProperty(value = "店铺Id")
    private String shopCode;

    /**
     * OrderDO-订单生成设备标识：posId、自助售货机ID等
     */
    @ApiModelProperty(value = "订单设备号(posId)")
    private String deviceId;

    /**
     * OrderDO-操作人id
     */
    @ApiModelProperty(value = "操作人", hidden = true)
    private String operatorId;

    /**
     * OrderDO-出库单号
     */
    @ApiModelProperty(value = "出库单号", hidden = true)
    private String outRecordCode;

    /**
     * OrderDO-发货时间
     */
    @ApiModelProperty(value = "物流发货时间", hidden = true)
    private Date shipmentTime;

    /**
     * OrderDO-物流单号
     */
    @ApiModelProperty(value = "物流单号")
    private String shipmentNo;

    /**
     * OrderDO-抵用劵
     */
    @ApiModelProperty(value = "抵用劵")
    private BigDecimal payTicket;

    /**
     * OrderDO-商品是否发成退货(0-未退，1-已退)
     */
    @ApiModelProperty(value = "是否发起退货0-未退，1-已退", hidden = true)
    private Short isReverse;

    @ApiModelProperty("仓库id")
    private String warehouseCode;

    @ApiModelProperty("云餐饮数据json大字段")
    private JSONObject restaurantData;

    @ApiModelProperty("订单分类 (商城订单, pos订单,云餐饮,云医馆,其他)")
    private Short orderClassify;

    @ApiModelProperty("销售类型(实物订单,服务订单,储值卡充值订单)")
    private Short saleType;
    private String saleTypeName;

    @ApiModelProperty(value = "1不用审核 ; 0 审核通过; 1 待审核 ; 2 审核未通过（风控）")
    private Short auditLabel;

    @ApiModelProperty("渠道 OMO商城渠道  新分销渠道  云餐饮")
    private String channelId;

    @ApiModelProperty(value = "成交时间(收货后客户确认时间)")
    private Date orderDateEnd;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    @ApiModelProperty("手工减去金额 (记录优惠信息)")
    private BigDecimal lostAmount;

    @ApiModelProperty("履约单号")
    private String fulfillOrderNo;

    @ApiModelProperty("抵扣的积分")
    private Long deductionPoint;

    @ApiModelProperty("积分抵扣的金额")
    private BigDecimal deductionPointAmount;

    @ApiModelProperty("本次订单获得的积分（退货按比例退）")
    private Long addPoint;

    @ApiModelProperty("接单时间")
    private Date receiveOrderTime;

    @ApiModelProperty("收货时间")
    private Date receiveGoodsTime;

    @ApiModelProperty("履约方式")
    private Integer deliveryType;

    private String deliveryTypeName;
    //======================


    @ApiModelProperty(value = "订单收货地址")
    private AddressDTO address;

    @ApiModelProperty(value = "订单列表")
    private List<OrderLineDTO> orderLineList;


    @ApiModelProperty(value = "父订单", hidden = true)
    private OrderDTO parent;

    @ApiModelProperty(value = "子订单列表", hidden = true)
    private List<OrderDTO> childOrders;

    @ApiModelProperty(value = "交易流程", hidden = true)
    private List<ActionDTO> nextAction;

    @ApiModelProperty(value = "会员等级", hidden = true)
    private String memberLevel;

    @ApiModelProperty(value = "appId", hidden = true)
    private String appId;

    @ApiModelProperty(value = "购物车对应id列表", hidden = true)
    private List<Long> cartIds;

    @ApiModelProperty(value = "活动优惠", hidden = true)
    List<OrderPromotionDTO> orderPromotionDoList;

    //=========以下是计算优惠活动要的信息=============
    /**
     * 赠品列表
     */
    private List<GiftDTO> giftList;

    /**
     * 换购商品列表
     */
    private List<PurchaseDTO> purchaseList;


    //===========以下是统计加的字段===========
    /**
     * tc_order_line-计算订单商品总数量
     */
    private BigDecimal sumQuantity;

    /**
     * 消费总次数
     */
    private BigDecimal totalCount;


    /**
     * 实际消费总金额
     */
    private BigDecimal totalActAmout;

    /**
     * 消费总件数
     */
    private BigDecimal totalQuantity;


    /**
     * 平均消费折扣
     */
    private BigDecimal aveDiscount;

    /**
     * 客单价
     */
    private BigDecimal aveAmount;

    /**
     * 退款总次数
     */
    private BigDecimal totalRevCount;

    /**
     * 退款总金额
     */
    private BigDecimal totalRevAmount;


    /**
     * 发票信息
     */
    private List<OrderInvoiceDTO> orderInvoiceDTOS;
    /**
     * 物流信息
     */
    private List<OrderShipmentDTO> orderShipmentDTOS;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discountAmount;

    @ApiModelProperty("仓库名称")
    private String warehouseName;

    @ApiModelProperty("店铺名称")
    private String shopName;

    /**
     * 支付信息
     */
    @ApiModelProperty("支付信息")
    private List<OrderPaymentDTO> orderPaymentDTOS;

    /**
     * 退货退款信息
     */
    private List<ReverseOrderDTO> reverseOrderDTOS;


    @ApiModelProperty("审核状态名称")
    private String auditLabelName;

    @ApiModelProperty("取货码")
    private String sendCode;

    @ApiModelProperty("优惠活动汇总")
    private List<OrderActiveRecordDTO> orderActiveRecordDTOList;
}
