package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTriggerHistoryDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.EventTriggerHistoryDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.repository.MarketingRepository;
import org.activiti.engine.delegate.DelegateExecution;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 测试流程结束节点
 */
public class FlowFinishActionHandlerTest extends MockitoTest {

    @Autowired
    private FlowFinishActionHandler flowFinishActionHandler;

    @Mock
    private EventTriggerHistoryDOMapper eventTriggerHistoryDOMapper;
    @InjectMocks
    @Autowired
    private MarketingRepository marketingRepository;

    @Transactional
    @Test
    public void doBusinessTest() {
        DelegateExecution delegateExecution = Mockito.mock(DelegateExecution.class);
        when(delegateExecution.getVariable("marketingType")).thenReturn("2");
        when(delegateExecution.getVariable("merchantCode")).thenReturn("abc");
        when(delegateExecution.getVariable("eventTriggerHistoryId")).thenReturn(111L);

        when(eventTriggerHistoryDOMapper.updateByPrimaryKeySelective(any(EventTriggerHistoryDO.class))).thenReturn(1);

        flowFinishActionHandler.doBusiness(delegateExecution, "");

//        verify(eventTriggerHistoryDOMapper).updateByPrimaryKeySelective(any(EventTriggerHistoryDO.class));
    }

}
