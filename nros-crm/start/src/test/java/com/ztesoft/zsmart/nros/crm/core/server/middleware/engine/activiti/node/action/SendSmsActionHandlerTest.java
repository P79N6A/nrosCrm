package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action;

import com.alibaba.fastjson.JSONObject;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDetailDTO;
import org.activiti.engine.delegate.DelegateExecution;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * 发送短信动作测试
 */
public class SendSmsActionHandlerTest extends MockitoTest {

    @Autowired
    private SendSmsActionHandler sendSmsActionHandler;

    @Test
    public void batchSendDynamicsSmsTest() {
        List<TargetUserDTO> targetUserListYes = getTargetUserListYes();

        DelegateExecution delegateExecution = Mockito.mock(DelegateExecution.class);
        when(delegateExecution.getVariable("targetUserListYes")).thenReturn(targetUserListYes);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("smsContent", "请尽快前往${address}取货");
        // jsonObject.put("copy", "15951886084");
        String nodeJsonParam = jsonObject.toJSONString();

        // sendSmsActionHandler.doBusiness(delegateExecution, nodeJsonParam);
    }

    @Test
    public void batchSendSmsTest() {
        List<TargetUserDTO> targetUserListYes = getTargetUserListYes();
        DelegateExecution delegateExecution = Mockito.mock(DelegateExecution.class);
        when(delegateExecution.getVariable("targetUserListYes")).thenReturn(targetUserListYes);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("smsContent", "请尽快");
        String nodeJsonParam = jsonObject.toJSONString();

        // sendSmsActionHandler.doBusiness(delegateExecution, nodeJsonParam);
    }

    private List<TargetUserDTO> getTargetUserListYes() {
        List<TargetUserDTO> targetUserListYes = new ArrayList<>();
        TargetUserDTO memberDetailDTO = new TargetUserDTO();
        memberDetailDTO.setId(2L);
        memberDetailDTO.setPhone("15951886084");
        targetUserListYes.add(memberDetailDTO);
        return targetUserListYes;
    }

}
