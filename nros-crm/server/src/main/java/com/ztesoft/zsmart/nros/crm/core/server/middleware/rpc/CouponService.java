package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc;

import java.util.List;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponReceiveRecordsDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponStatisticsDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponBatchSendParam;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponDefineQuery;

/**
 * @author zhou.xiaofeng
 * @description 优惠券接口订阅
 * @date 2019-06-11
 */
public interface CouponService {
    /**
     * 查询可用的优惠券列表
     *
     * @param couponDefineQuery
     * @return
     */
    ResponseMsg<List<CouponDTO>> listAvailableCoupon(CouponDefineQuery couponDefineQuery);

    /**
     * 根据优惠券编码查询优惠券领取列表
     *
     * @param couponCode 优惠券编码
     * @return
     */
    ResponseMsg<List<CouponReceiveRecordsDTO>> listReceiveMember(String couponCode);

    /**
     * 根据优惠券编码查询优惠券核销列表
     *
     * @param couponCode 优惠券编码
     * @return
     */
    ResponseMsg<List<CouponReceiveRecordsDTO>> listConsumeMember(String couponCode);

    /**
     * 批量发送优惠券方法
     *
     * @param couponBatchSendParam
     * @return
     */
    ResponseMsg<List<CouponReceiveRecordsDTO>> batchSend(CouponBatchSendParam couponBatchSendParam);

    /**
     * 查询营销流程对应的优惠券领取/核销统计数据
     * 
     * @param activeInstanceCode
     * @param activeCode
     * @param startDate
     * @param endDate
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg<CouponStatisticsDTO>
     * @author PQ
     * @date 2019/7/30
     */
    ResponseMsg<CouponStatisticsDTO> statisticsOfReservedAndChecked(String activeInstanceCode, String activeCode,
        String startDate, String endDate);

}
