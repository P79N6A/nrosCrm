package com.ztesoft.zsmart.nros.crm.core.server.repository;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTraceListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTraceQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.EventTraceConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTraceDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTraceTemplateDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.EventTraceMapper;
import com.ztesoft.zsmart.nros.crm.core.server.domain.eventTrace.model.EventTraceBO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

/**
 * @author yuanxiaokai
 * @date 2019/7/15
 */
public class EventTraceRepositoryTest extends MockitoTest {

    @Autowired
    @InjectMocks
    private EventTraceRepository eventTraceRepository;

    @Mock
    private EventTraceMapper eventTraceMapper;

    @Test
    public void testListTrendTrail() {
        EventTraceQuery eventTraceQuery = new EventTraceQuery();
        eventTraceQuery.setEventCode("1");
        EventTraceDO eventTraceDO = new EventTraceDO();
        eventTraceDO.setEventId(1L);
        Mockito.when(eventTraceMapper.listTrendTrail(eventTraceDO)).thenReturn(Collections.emptyList());
        Mockito.when(eventTraceMapper.getEventIdByEventCode(eventTraceQuery.getEventCode())).thenReturn(1L);
        Assert.assertEquals(new PageInfo<>(Collections.emptyList()).toString(),
            eventTraceRepository.listTrendTrail(eventTraceQuery).toString());
    }

    @Test
    public void testTraceTemplateByEventCode() {
        Mockito.when(eventTraceMapper.traceTemplateByEventCode("1", "zh")).thenReturn(new EventTraceTemplateDO());
        Assert.assertEquals(new EventTraceListDTO(), eventTraceRepository.traceTemplateByEventCode("1"));
    }

    @Test
    public void insertEventTrace() {
        EventTraceBO eventTraceBO = new EventTraceBO();
        Mockito.doNothing().when(eventTraceMapper).insertEventTrace(EventTraceConvertor.INSTANCE.boToDO(eventTraceBO));
        eventTraceRepository.insertEventTrace(eventTraceBO);
    }

    @Test
    public void testListEventNameCode() {
        Mockito.when(eventTraceMapper.listEventNameCode()).thenReturn(Collections.emptyList());
        Assert.assertEquals(Collections.emptyList(), eventTraceRepository.listEventNameCode());
    }

}
