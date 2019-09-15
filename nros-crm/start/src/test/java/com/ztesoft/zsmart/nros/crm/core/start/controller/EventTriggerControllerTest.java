package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.ztesoft.zsmart.nros.base.zmq.entity.NrosMQMessage;
import com.ztesoft.zsmart.nros.base.zmq.producer.DefaultZMQProducer;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerParam;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;


import static org.mockito.ArgumentMatchers.any;

/**
 * @author yuanxiaokai
 * @date 2019/7/22
 */
public class EventTriggerControllerTest extends MockitoTest {

    @Mock
    private DefaultZMQProducer defaultZMQProducer;

    @Autowired
    @InjectMocks
    private EventTriggerController eventTriggerController;

    @Mock
    private RuntimeService runtimeService;

    @Mock
    private RepositoryService repositoryService;

    @Test
    public void testRegisterTrigger() {
        EventTriggerParam eventTriggerParam = new EventTriggerParam();
        eventTriggerParam.setMemberId(1L);
        Mockito.when(defaultZMQProducer.sendMessage(any(Producer.class), any(NrosMQMessage.class)))
            .thenReturn(new SendResult());
        eventTriggerController.registerTrigger(eventTriggerParam);
    }

    /*
     * @Test public void testStartActiviti() { List<ProcessDefinition> processDefinitionList = Lists.newArrayList();
     * Mockito .when(repositoryService.createProcessDefinitionQuery()
     * .processDefinitionKey(PromotionContants.ACTIVITI_PROCESS_PREFIX + "1").latestVersion().list())
     * .thenReturn(processDefinitionList);
     * Mockito.when(runtimeService.startProcessInstanceByKey(PromotionContants.ACTIVITI_PROCESS_PREFIX + "1",
     * Maps.newHashMap())).thenReturn(null); eventTriggerController.startActiviti(1L); }
     */

}
