package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponReceiveRecordsDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponBatchSendParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.CouponService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponDefineQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Objects;

/**
 * @author zhou.xiaofeng
 * @description 优惠券controller
 * @date 2019-06-11
 */
@SessionController
@RequestMapping("/coupon")
@Api(value = "优惠券", tags = {
    "优惠券"
})
public class CouponController {
    @Autowired
    private CouponService couponService;

    @PostMapping(value = "/list")
    @ApiOperation(value = "查询可用优惠券列表", notes = "查询优惠券列表")
    public ResponseMsg<List<CouponDTO>> listCoupon(@RequestBody CouponDefineQuery defineQuery) {
        return couponService.listAvailableCoupon(defineQuery);
    }

    /**
     * 批量发送优惠券方法
     *
     * @param couponBatchSendParam
     * @return
     */
    @PostMapping("/batch-send")
    @ApiOperation("批量发送优惠券方法")
    public ResponseMsg batchSend(@RequestBody CouponBatchSendParam couponBatchSendParam) {
        ResponseMsg<List<CouponReceiveRecordsDTO>> responseMsg = couponService.batchSend(couponBatchSendParam);
        List<CouponReceiveRecordsDTO> data = responseMsg.getData();
        if (Objects.equals("1", data.get(0).getSendSuccess())) {
            return responseMsg;
        }
        return new ResponseMsg(data.get(0).getErrorMsg()).fail().setMessage(data.get(0).getErrorMsg());
    }

}
