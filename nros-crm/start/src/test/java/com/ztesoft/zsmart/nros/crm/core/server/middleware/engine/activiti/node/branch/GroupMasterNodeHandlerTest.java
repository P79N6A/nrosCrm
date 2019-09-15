package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch;

import com.alibaba.fastjson.JSONArray;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
public class GroupMasterNodeHandlerTest extends MockitoTest {

    @Autowired
    private GroupMasterNodeHandler groupMasterNodeHandler;

    @Test
    public void doBusinessTest() {
        DelegateExecution delegateExecution = Mockito.mock(DelegateExecution.class);
        when(delegateExecution.getCurrentActivityId()).thenReturn("111");
        when(delegateExecution.getVariable("marketingType")).thenReturn("1");

        List<TargetUserDTO> targetUserListYes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TargetUserDTO memberDetailDTO = new TargetUserDTO();
            memberDetailDTO.setName("memberName" + i);
            targetUserListYes.add(memberDetailDTO);
        }

        when(delegateExecution.getVariable("targetUserListYes")).thenReturn(targetUserListYes);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject rate1 = new JSONObject();
        rate1.put("rate", 20);
        jsonArray.add(rate1);
        JSONObject rate2 = new JSONObject();
        rate2.put("rate", 80);
        jsonArray.add(rate2);

        jsonObject.put("groupWay", 0);
        jsonObject.put("groupMasterParam", jsonArray);

        // when(delegateExecution.setVariable("groupMasterParams_" + delegateExecution.getCurrentActivityId(),
        // any(Map.class)))

        groupMasterNodeHandler.doBusiness(delegateExecution, jsonObject.toJSONString());
        // verify(delegateExecution).setVariable("groupMasterParams_" + delegateExecution.getCurrentActivityId(),
        // groupMasterParams);

        verify(delegateExecution).getVariable("targetUserListYes");
    }

}
