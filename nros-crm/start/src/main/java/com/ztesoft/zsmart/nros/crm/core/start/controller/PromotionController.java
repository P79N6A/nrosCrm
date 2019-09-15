package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.PromotionService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponInstanceDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponMemberIdQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@SessionController
@RequestMapping("/promotion")
@Api(value = "营销管理", tags = {"营销管理"})
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping("/list-coupon-instance-by-member-id")
    @ApiOperation("查询会员优惠券实例列表")
    public ResponseMsg<List<CouponInstanceDTO>> listCouponInstanceByMemberId(@RequestBody CouponMemberIdQuery couponMemberIdQuery) {
        return promotionService.listCouponInstanceByMemberId(couponMemberIdQuery);
    }
}
