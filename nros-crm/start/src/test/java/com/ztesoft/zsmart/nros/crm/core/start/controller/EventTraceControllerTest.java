package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTraceQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTraceDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.EventTraceMapper;
import com.ztesoft.zsmart.nros.crm.core.server.repository.EventTraceRepository;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author yuanxiaokai
 * @date 2019/7/10
 */
public class EventTraceControllerTest extends MockitoTest {

    @Autowired
    private EventTraceController eventTraceController;

    @Autowired
    @InjectMocks
    private EventTraceRepository eventTraceRepository;

    @Mock
    private EventTraceMapper eventTraceMapper;

    @Test
    public void testListTrendTrail() {
        EventTraceQuery eventTraceQuery = new EventTraceQuery();
        eventTraceQuery.setMemberId(4L);
        eventTraceQuery.setEventCode("code");
        Mockito.when(eventTraceMapper.getEventIdByEventCode(eventTraceQuery.getEventCode())).thenReturn(1L);
        EventTraceDO eventTraceDO = new EventTraceDO();
        eventTraceDO.setEventId(11L);
        List<EventTraceDO> eventTraceLists = Lists.newArrayList(eventTraceDO);
        Mockito.when(eventTraceMapper.listTrendTrail(any())).thenReturn(eventTraceLists);
        Assert.assertNotNull(eventTraceController.listTrendTrail(eventTraceQuery).getData());
    }

    @Test
    public void testListEventNameCode() {
        Mockito.when(eventTraceMapper.listEventNameCode()).thenReturn(new ArrayList<>());
        Assert.assertNotNull(eventTraceController.listEventNameCode().getData());
    }

}
