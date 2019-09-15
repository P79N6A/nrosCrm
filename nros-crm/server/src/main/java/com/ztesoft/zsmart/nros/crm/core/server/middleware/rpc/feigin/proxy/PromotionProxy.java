package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponInstanceDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponMemberIdQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 营销中心接口订阅
 *
 * @author litao
 * @Date 2019/6/17
 */
@FeignClient(value = "promotion")
public interface PromotionProxy {

    /**
     * 查询会员优惠券实例列表
     *
     * @param couponMemberIdQuery
     * @return
     */
    @RequestMapping(value = "/nrosapi/promotion/v1/coupon/list-coupon-instance-by-member-id", method = RequestMethod.POST)
    ResponseMsg<List<CouponInstanceDTO>> listCouponInstanceByMemberId(@RequestBody CouponMemberIdQuery couponMemberIdQuery);
}
