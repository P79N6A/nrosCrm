package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponInstanceDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponMemberIdQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 营销中心远程调用接口定义
 *
 * @author litao
 * @Date 2019/6/17
 */
public interface PromotionService {

    /**
     * 查询会员优惠券实例列表
     * 
     * @param couponMemberIdQuery
     * @return
     */
    ResponseMsg<List<CouponInstanceDTO>> listCouponInstanceByMemberId(
        @RequestBody CouponMemberIdQuery couponMemberIdQuery);
}
