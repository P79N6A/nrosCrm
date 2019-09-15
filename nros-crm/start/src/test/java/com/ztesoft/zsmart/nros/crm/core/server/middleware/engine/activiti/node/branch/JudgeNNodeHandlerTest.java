package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDetailDTO;

/**
 * 测试NO分支
 */
public class JudgeNNodeHandlerTest extends MockitoTest {
    @Autowired
    private JudgeNNodeHandler judgeNNodeHandler;

    @Test
    public void doBusinessTest() {
        String parentId = "111";
        DelegateExecution delegateExecution = Mockito.mock(DelegateExecution.class);

        List<TargetUserDTO> memberDetailDTOList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TargetUserDTO memberDetailDTO = new TargetUserDTO();
            memberDetailDTO.setId(Long.valueOf(i));
            memberDetailDTO.setName("memberName" + i);
            memberDetailDTOList.add(memberDetailDTO);
        }

        List<TargetUserDTO> tempTargetUserList = new ArrayList<>();
        tempTargetUserList.add(memberDetailDTOList.get(0));
        tempTargetUserList.add(memberDetailDTOList.get(1));

        when(delegateExecution.getVariable("targetUserListYes_" + parentId)).thenReturn(memberDetailDTOList);
        when(delegateExecution.getVariable("tempTargetUserList_" + parentId)).thenReturn(tempTargetUserList);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("parentActivityId", parentId);

        List<TargetUserDTO> resultList = new ArrayList<>();
        resultList.add(memberDetailDTOList.get(2));

        when(delegateExecution.setVariableLocal("targetUserListYes", resultList)).thenReturn(null);

        judgeNNodeHandler.doBusiness(delegateExecution, jsonObject.toJSONString());

        verify(delegateExecution).setVariableLocal("targetUserListYes", resultList);

    }

}
