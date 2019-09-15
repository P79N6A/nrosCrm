package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.PromotionService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.PromotionProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponInstanceDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponMemberIdQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionProxy promotionProxy;

    /**
     *  查询会员优惠券实例列表
     *
     * @param couponMemberIdQuery
     * @return
     */
    @Override
    public ResponseMsg<List<CouponInstanceDTO>> listCouponInstanceByMemberId(CouponMemberIdQuery couponMemberIdQuery) {
        log.info("start to PromotionServiceImpl listCouponInstanceByMemberId...");
        return promotionProxy.listCouponInstanceByMemberId(couponMemberIdQuery);
    }
}
