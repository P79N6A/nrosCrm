package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.MemberServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.MemberProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDetailDTO;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 打标签动作测试
 */
public class TaggedActionHandlerTest extends MockitoTest {

    @Autowired
    private TaggedActionHandler taggedActionHandler;

    @Mock
    private MemberProxy memberProxy;
    @InjectMocks
    @Autowired
    private MemberServiceImpl memberService;

    @Test
    public void testHandler() {
        List<TargetUserDTO> targetUserListYes = new ArrayList<>();
        TargetUserDTO memberDetailDTO = new TargetUserDTO();
        memberDetailDTO.setId(2L);
        targetUserListYes.add(memberDetailDTO);

        JSONArray tagIds = new JSONArray();
        tagIds.add(1L);
        JSONArray tagTypes = new JSONArray();
        tagTypes.add("A");

        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("tagIds", tagIds);
        jsonInfo.put("tags", tagTypes);

        String json=jsonInfo.toJSONString();

        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setSuccess(true);
        when(memberProxy.saveMemberTagBatch(anyList())).thenReturn(responseMsg);

        taggedActionHandler.handler(json,targetUserListYes);

//        verify(memberProxy).saveMemberTagBatch(anyList());
    }

}
