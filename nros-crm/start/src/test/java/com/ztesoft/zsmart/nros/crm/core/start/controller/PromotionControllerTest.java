package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.PromotionServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.PromotionProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponInstanceDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponMemberIdQuery;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * 营销单元测试
 */
public class PromotionControllerTest extends MockitoTest {

    @Autowired
    private PromotionController promotionController;

    @Mock
    private PromotionProxy promotionProxy;

    @InjectMocks
    @Autowired
    private PromotionServiceImpl promotionServiceImpl;

    @Test
    public void listCouponInstanceByMemberIdTest() {
        CouponMemberIdQuery couponMemberIdQuery = new CouponMemberIdQuery();
        couponMemberIdQuery.setMemberId(1L);

        ResponseMsg<List<CouponInstanceDTO>> rtnList = new ResponseMsg<>();
        List<CouponInstanceDTO> list = new ArrayList<>();
        CouponInstanceDTO dto = new CouponInstanceDTO();
        dto.setCouponCode("test");
        list.add(dto);
        rtnList.setData(list);

        when(promotionProxy.listCouponInstanceByMemberId(couponMemberIdQuery)).thenReturn(rtnList);

        ResponseMsg<List<CouponInstanceDTO>> responseMsg = promotionController.listCouponInstanceByMemberId(couponMemberIdQuery);
        assertTrue(responseMsg.getData().get(0).getCouponCode().equals("test"));

    }
}
