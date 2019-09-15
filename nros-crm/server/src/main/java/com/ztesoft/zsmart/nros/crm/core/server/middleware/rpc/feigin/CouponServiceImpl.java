package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.base.util.CommonFunctions;
import com.ztesoft.zsmart.nros.crm.core.server.common.constant.PromotionContants;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.CouponService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.CouponProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponReceiveRecordsDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponStatisticsDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponBatchSendParam;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponDefineQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponReceiveListQuery;

import lombok.Setter;

/**
 * @author zhou.xiaofeng
 * @description
 * @date 2019-06-11
 */
@Service
@Setter
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponProxy couponProxy;

    /**
     * 查询可用的优惠券列表
     *
     * @param couponDefineQuery
     * @return
     */
    @Override
    public ResponseMsg<List<CouponDTO>> listAvailableCoupon(CouponDefineQuery couponDefineQuery) {
        couponDefineQuery.setStartDate(new Date());
        couponDefineQuery.setCouponStatus("1");
        return couponProxy.listCoupon(couponDefineQuery);
    }

    /**
     * 根据优惠券编码查询优惠券领取列表
     *
     * @param couponCode 优惠券编码
     * @return
     */
    @Override
    public ResponseMsg<List<CouponReceiveRecordsDTO>> listReceiveMember(String couponCode) {
        return CommonFunctions.runSupplierByList(() -> this.innerListCouponMember(couponCode, "0"),
            "根据优惠券编码查询优惠券领取列表失败！");
    }

    /**
     * 根据优惠券编码查询优惠券核销列表
     *
     * @param couponCode 优惠券编码
     * @return
     */
    @Override
    public ResponseMsg<List<CouponReceiveRecordsDTO>> listConsumeMember(String couponCode) {
        return CommonFunctions.runSupplierByList(() -> this.innerListCouponMember(couponCode, "1"),
            "根据优惠券编码查询优惠券核销列表失败！");
    }

    /**
     * 批量发送优惠券方法
     *
     * @param couponBatchSendParam
     * @return
     */
    @Override
    public ResponseMsg<List<CouponReceiveRecordsDTO>> batchSend(CouponBatchSendParam couponBatchSendParam) {
        return couponProxy.batchSend(couponBatchSendParam);
    }

    /**
     * 查询营销流程对应的优惠券领取/核销统计数据
     * 
     * @param activeInstanceCode
     * @param activeCode
     * @param startDate
     * @param endDate
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg<java.util.Map>
     * @author PQ
     * @date 2019/7/30
     */
    @Override
    public ResponseMsg<CouponStatisticsDTO> statisticsOfReservedAndChecked(String activeInstanceCode, String activeCode,
        String startDate, String endDate) {
        return couponProxy.statisticsOfReservedAndChecked(activeInstanceCode, activeCode, startDate, endDate);
    }

    /**
     * 加载优惠券领取用户列表
     * 
     * @param couponCode 优惠券编码
     * @param isUse 是否核销：1、已使用；2、未使用
     * @return java.util.List<CouponReceiveRecordsDTO>
     * @author PQ
     * @date 2019/7/11
     */
    private List<CouponReceiveRecordsDTO> innerListCouponMember(String couponCode, String isUse) {
        int pageIndex = 1;
        CouponReceiveListQuery couponReceiveListQuery = new CouponReceiveListQuery();
        couponReceiveListQuery.setCouponCode(couponCode);
        couponReceiveListQuery.setIsUse(isUse);
        couponReceiveListQuery.setPageIndex(pageIndex);
        couponReceiveListQuery.setPageSize(PromotionContants.MAX_QUERY_PAGE_SIZE);
        ResponseMsg couponReceiveRecordsResp = couponProxy.listReceive(couponReceiveListQuery);
        Long totalRecord = couponReceiveRecordsResp.getTotal();
        List<CouponReceiveRecordsDTO> couponReceiveList = (List<CouponReceiveRecordsDTO>) couponReceiveRecordsResp
            .getData();
        while (totalRecord > couponReceiveList.size()) {
            couponReceiveListQuery.setPageIndex(++pageIndex);
            couponReceiveList
                .addAll((List<CouponReceiveRecordsDTO>) couponProxy.listReceive(couponReceiveListQuery).getData());
        }
        return couponReceiveList;
    }

}
