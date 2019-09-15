package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.CouponServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.CouponProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.CouponReceiveRecordsDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponReceiveListQuery;

/**
 * 测试获取优惠券领取成功的会员
 */
public class CouponGetJudgeNodeHandlerTest extends MockitoTest {
    @Autowired
    private CouponGetJudgeNodeHandler couponGetJudgeNodeHandler;

    @Mock
    private CouponProxy couponProxy;

    @InjectMocks
    @Autowired
    private CouponServiceImpl couponService;

    @Test
    public void operateTest() {
        JSONObject jsono = new JSONObject();
        jsono.put("selectedId", "DD201907101213308907061261041665");

        List<CouponReceiveRecordsDTO> list = new ArrayList<>();
        CouponReceiveRecordsDTO couponReceiveRecordsDTO = new CouponReceiveRecordsDTO();
        couponReceiveRecordsDTO.setMemberId(1L);
        couponReceiveRecordsDTO.setMemberName("test1");
        list.add(couponReceiveRecordsDTO);

        ResponseMsg couponReceiveRecordsResp = new ResponseMsg();
        couponReceiveRecordsResp.setData(list);
        couponReceiveRecordsResp.setTotal(2L);

        when(couponProxy.listReceive(any(CouponReceiveListQuery.class))).thenReturn(couponReceiveRecordsResp);

        List<TargetUserDTO> resultList = couponGetJudgeNodeHandler.operate(null, jsono.toJSONString());

        // assertTrue(resultList.get(0).getName().equals("test1"));
        // verify(couponProxy, Mockito.times(2)).listReceive(any(CouponReceiveListQuery.class));

    }

}
