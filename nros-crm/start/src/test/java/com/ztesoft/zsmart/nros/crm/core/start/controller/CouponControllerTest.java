package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.CouponServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.CouponProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponDefineQuery;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author yuanxiaokai
 * @date 2019/7/9
 */
public class CouponControllerTest extends MockitoTest {

    @Autowired
    private CouponController couponController;

    @InjectMocks
    @Autowired
    private CouponServiceImpl couponService;

    @Mock
    private CouponProxy couponProxy;

    @Test
    public void testListCoupon() {
        CouponDefineQuery couponDefineQuery = new CouponDefineQuery();
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setCouponCode("123456");
        List<CouponDTO> data = Lists.newArrayList(couponDTO);
        Mockito.when(couponProxy.listCoupon(couponDefineQuery)).thenReturn(ResponseMsg.build(data));
        Assert.assertEquals(data,couponController.listCoupon(couponDefineQuery).getData());
    }
}
