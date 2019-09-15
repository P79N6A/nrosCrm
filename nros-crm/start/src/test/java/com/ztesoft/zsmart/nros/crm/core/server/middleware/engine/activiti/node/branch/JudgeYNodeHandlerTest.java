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

/**
 * 测试YES分支
 */
public class JudgeYNodeHandlerTest extends MockitoTest {

    @Autowired
    private JudgeYNodeHandler judgeYNodeHandler;

    @Test
    public void doBusinessTest() {

        String parentId = "111";
        DelegateExecution delegateExecution = Mockito.mock(DelegateExecution.class);

        List<TargetUserDTO> memberDetailDTOList = new ArrayList<>();
        TargetUserDTO memberDetailDTO = new TargetUserDTO();
        memberDetailDTO.setId(1L);
        memberDetailDTO.setName("memberName1");
        memberDetailDTOList.add(memberDetailDTO);
        memberDetailDTO = new TargetUserDTO();
        memberDetailDTO.setId(2L);
        memberDetailDTO.setName("memberName2");
        memberDetailDTOList.add(memberDetailDTO);

        List<TargetUserDTO> tempTargetUserList = new ArrayList<>();
        memberDetailDTO = new TargetUserDTO();
        memberDetailDTO.setId(2L);
        memberDetailDTO.setName("memberName2");
        tempTargetUserList.add(memberDetailDTO);

        when(delegateExecution.getVariable("targetUserListYes_" + parentId)).thenReturn(memberDetailDTOList);
        when(delegateExecution.getVariable("tempTargetUserList_" + parentId)).thenReturn(tempTargetUserList);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("parentActivityId", parentId);

        List<TargetUserDTO> resultList = new ArrayList<>();
        resultList.add(tempTargetUserList.get(0));

        when(delegateExecution.setVariableLocal("targetUserListYes", resultList)).thenReturn(null);

        judgeYNodeHandler.doBusiness(delegateExecution, jsonObject.toJSONString());

        verify(delegateExecution).setVariableLocal("targetUserListYes", resultList);

    }
}
