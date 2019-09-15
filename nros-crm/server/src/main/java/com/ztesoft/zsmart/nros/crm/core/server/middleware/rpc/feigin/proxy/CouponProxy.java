package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponReceiveRecordsDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponStatisticsDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponBatchSendParam;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponDefineQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponReceiveListQuery;

/**
 * @author zhou.xiaofeng
 * @description 优惠券接口订阅
 * @date 2019-06-11
 */
@FeignClient(value = "promotion")
public interface CouponProxy {

    /**
     * 查询可用的优惠券列表
     *
     * @param couponDefineQuery
     * @return
     */
    @PostMapping(value = "/nrosapi/promotion/v1/coupon/list-all")
    ResponseMsg<List<CouponDTO>> listCoupon(@RequestBody CouponDefineQuery couponDefineQuery);

    /**
     * 根据优惠券编码查询优惠券领取列表
     *
     * @param couponReceiveListQuery
     * @return //
     */
    @PostMapping(value = "/nrosapi/promotion/v1/coupon/list-receive")
    ResponseMsg<List<CouponReceiveRecordsDTO>> listReceive(@RequestBody CouponReceiveListQuery couponReceiveListQuery);

    /**
     * 批量发送优惠券方法
     *
     * @param couponBatchSendParam
     * @return
     */
    @PostMapping(value = "/nrosapi/promotion/v1/coupon/batch-send")
    ResponseMsg<List<CouponReceiveRecordsDTO>> batchSend(@RequestBody CouponBatchSendParam couponBatchSendParam);

    /**
     * 查询营销流程对应的优惠券领取/核销统计数据
     * 
     * @param activeInstanceCode
     * @param activeCode
     * @param startDate
     * @param endDate
     * @return ResponseMsg<CouponStatisticsDTO>
     * @author PQ
     * @date 2019/7/30
     */
    @GetMapping("/nrosapi/promotion/v1/coupon/statistics-reserved-checked")
    ResponseMsg<CouponStatisticsDTO> statisticsOfReservedAndChecked(
        @RequestParam(value = "activeInstanceCode") String activeInstanceCode,
        @RequestParam("activeCode") String activeCode,
        @RequestParam(value = "startDate", required = false) String startDate,
        @RequestParam(value = "endDate", required = false) String endDate);

}
