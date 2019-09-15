package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.filter;

import com.alibaba.fastjson.JSONObject;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.MemberServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.MemberProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberListDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagQuery;
import org.activiti.engine.delegate.DelegateExecution;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 测试客群筛选
 */
public class FilterNodeHandlerTest extends MockitoTest {

    @Autowired
    private FilterNodeHandler filterNodeHandler;

    @Mock
    private MemberProxy memberProxy;
    @InjectMocks
    @Autowired
    private MemberServiceImpl memberService;

    @Test
    public void doBusinessTest() {
        DelegateExecution delegateExecution = Mockito.mock(DelegateExecution.class);
        when(delegateExecution.getVariable("marketingType")).thenReturn("2");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groupId", 1L);

        ResponseMsg<List<MemberListDTO>> responseMsg = new ResponseMsg<>();
        List<MemberListDTO> memberListDTOList = new ArrayList<>();
        MemberListDTO memberListDTO = new MemberListDTO();
        memberListDTO.setName("test");
        memberListDTO.setId(1L);
        memberListDTO.setPhone("15911111111");
        memberListDTOList.add(memberListDTO);
        responseMsg.setData(memberListDTOList);

        responseMsg.setTotal(1L);

        when(memberProxy.qyMemberByTag(any(TagQuery.class))).thenReturn(responseMsg);

        filterNodeHandler.doBusiness(delegateExecution, jsonObject.toJSONString());

//        verify(memberProxy).qyMemberByTag(any(TagQuery.class));

    }
}
