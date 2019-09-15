package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param;

import com.ztesoft.zsmart.nros.common.model.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReverseOrderQuery extends BaseQuery {
    @ApiModelProperty(value = "订单号")
    Long orderNo;

    @ApiModelProperty(value = "买家ID")
    Long buyerId;

    @ApiModelProperty(value = "卖家ID")
    Long sellerId;

    @ApiModelProperty(value = "退换货单号")
    Long reverseOrderNo;

    @ApiModelProperty(value = "商品名称")
    String itemName;

    @ApiModelProperty(value = "退换货单状态(9.待审核 10.审核拒绝 11.审核通过 20.待退款 22.退款成功 23 买家发货 24.卖家收货 28.退货交易关闭)")
    Short tradeStatus;

    @ApiModelProperty("订单开始时间")
    Date startTime;

    @ApiModelProperty("订单结束时间")
    Date endTime;

    @ApiModelProperty(value = "退货单来源")
    private Short reverseOrderSource;

    @ApiModelProperty(value = "店铺ID")
    private String shopCode;

    @ApiModelProperty(value = "退款类型")
    private Short tradeType;

    @ApiModelProperty(value = "appId", hidden = true)
    private String appId;

    @ApiModelProperty(value = "会员id")
    private Long memberCardId;
}
