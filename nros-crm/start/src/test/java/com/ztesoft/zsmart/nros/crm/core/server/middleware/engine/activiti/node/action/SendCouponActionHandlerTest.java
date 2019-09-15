package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.CouponServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.CouponProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponBatchSendParam;

/**
 * 赠送优惠券动作测试
 */
public class SendCouponActionHandlerTest extends MockitoTest {

    @Autowired
    private SendCouponActionHandler sendCouponActionHandler;

    @Mock
    private CouponProxy couponProxy;

    @InjectMocks
    @Autowired
    private CouponServiceImpl couponService;

    @Test
    public void doBusinessTest() {
        List<TargetUserDTO> targetUserListYes = new ArrayList<>();
        TargetUserDTO memberDetailDTO = new TargetUserDTO();
        memberDetailDTO.setId(2L);
        memberDetailDTO.setName("testName");
        targetUserListYes.add(memberDetailDTO);

        DelegateExecution delegateExecution = Mockito.mock(DelegateExecution.class);
        when(delegateExecution.getVariable("marketingDefineId")).thenReturn(1L);
        when(delegateExecution.getVariable("marketingType")).thenReturn("A");
        when(delegateExecution.getVariable("merchantCode")).thenReturn("AAA");
        when(delegateExecution.getVariable("targetUserListYes")).thenReturn(targetUserListYes);
        when(delegateExecution.getProcessInstanceId()).thenReturn("AAA");
        when(delegateExecution.getCurrentActivityId()).thenReturn("AAA");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("couponCode", "E0004");
        String nodeJsonParam = jsonObject.toJSONString();

        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setSuccess(true);
        when(couponProxy.batchSend(any(CouponBatchSendParam.class))).thenReturn(responseMsg);

        sendCouponActionHandler.doBusiness(delegateExecution, nodeJsonParam);

        verify(delegateExecution).getVariable("targetUserListYes");
        // verify(couponProxy).batchSend(any(CouponBatchSendParam.class));

    }

}
