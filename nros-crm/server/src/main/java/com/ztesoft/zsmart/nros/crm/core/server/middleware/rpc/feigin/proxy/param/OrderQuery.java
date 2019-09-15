package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author wufan
 * @title: 订单查询入参
 * @date 2019/6/2 11:06
 */
@Data
public class OrderQuery extends BaseQuery {
    @ApiModelProperty(value = "订单编号")
    private Long orderNo;

    @ApiModelProperty(value = "用户ID")
    private Long buyerId;

    @ApiModelProperty(value = "会员ID")
    private Long memberCardId;

    @ApiModelProperty(value = "商家ID")
    private Long sellerId;

    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @ApiModelProperty(value = "订单状态(1.订单初始化  2.订单待支付 3.完成支付 4.完成阶段支付 5.完成订单派发 6.接受订单 7.待收货 8.已收货 -1.交易异常关闭 -2.交易超时关闭 -3.交易正常取消 0.交易成功)")
    private Short tradeStatus;

    @ApiModelProperty(value = "订单开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "订单结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "买家手机号")
    private String phone;

    @ApiModelProperty(value = "父单号")
    private Long parentOrderNo;

    @ApiModelProperty(value = "发货仓库")
    private Long wareHouseCode;

    @ApiModelProperty(value = "销售类型")
    private Short saleType;

    @ApiModelProperty(value = "订单来源")
    private Short orderSource;

    @ApiModelProperty(value = "用户ID列表", hidden = true)
    private List<Long> buyerIds;

    @ApiModelProperty(value = "操作人id")
    private String operatorId;

    @ApiModelProperty(value = "订单设备号(posId)")
    private String deviceId;

    @ApiModelProperty(value = "店铺账号")
    private String shopCode;

    @ApiModelProperty(value = "外部流水号")
    private String orderIdOut;

    @ApiModelProperty(value = "外部流水号列表")
    private List<String> orderIdOuts;

    @ApiModelProperty(value = "交易类型")
    private Short tradeType;

    @ApiModelProperty(value = "查询物理单", hidden = true)
    private Short orderType;

    @ApiModelProperty(value = "租户id")
    private String tenantId;

    @ApiModelProperty(value = "")
    private String appId;

    @ApiModelProperty(value = "支付状态(0-未支付，1-已支付)", hidden = true)
    private Short payStatus;
}
