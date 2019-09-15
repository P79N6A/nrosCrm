package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch;

import com.alibaba.fastjson.JSONObject;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDetailDTO;
import org.activiti.engine.delegate.DelegateExecution;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 测试获取当前分组的目标用户列表
 */
public class GroupBranchNodeHandlerTest extends MockitoTest {

    @Autowired
    private GroupBranchNodeHandler groupBranchNodeHandler;

    @Test
    public void operateTest() {
        String parentId = "9999";
        DelegateExecution delegateExecution = Mockito.mock(DelegateExecution.class);
        when(delegateExecution.getCurrentActivityId()).thenReturn("111");
        when(delegateExecution.getId()).thenReturn("222");
        when(delegateExecution.getParentId()).thenReturn("333");

        List<TargetUserDTO> memberDetailDTOList = new ArrayList<>();
        TargetUserDTO memberDetailDTO = new TargetUserDTO();
        memberDetailDTO.setName("memberName");
        memberDetailDTOList.add(memberDetailDTO);

        List<int[]> indexes = new ArrayList<>();
        int[] index = new int[]{0};
        indexes.add(index);

        Map<String, Object> map = new HashMap<>();
        map.put("targetUserListYes", memberDetailDTOList);
        map.put("userIndexes", indexes);


        when(delegateExecution.getVariable("marketingType")).thenReturn("1");
        when(delegateExecution
                .getVariable("groupMasterParams_" + parentId)).thenReturn(map);

        when(delegateExecution.setVariableLocal("targetUserListYes", memberDetailDTOList)).thenReturn(null);

        JSONObject jsono = new JSONObject();
        jsono.put("parentActivityId", parentId);
        jsono.put("groupWay", 2);
        jsono.put("groupId", 1);

        groupBranchNodeHandler.doBusiness(delegateExecution, jsono.toJSONString());

        verify(delegateExecution).setVariableLocal("targetUserListYes", memberDetailDTOList);
    }
}
