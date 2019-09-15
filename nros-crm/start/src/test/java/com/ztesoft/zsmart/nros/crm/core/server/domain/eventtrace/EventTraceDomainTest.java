package com.ztesoft.zsmart.nros.crm.core.server.domain.eventtrace;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTraceListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.PullDownListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTraceParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTraceQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.EventTraceConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.domain.eventTrace.EventTraceDomain;
import com.ztesoft.zsmart.nros.crm.core.server.repository.EventTraceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * @author yuanxiaokai
 * @date 2019/7/16
 */
public class EventTraceDomainTest extends MockitoTest {

    @Autowired
    @InjectMocks
    private EventTraceDomain eventTraceDomain;

    @Mock
    private EventTraceRepository eventTraceRepository;

    @Test
    public void testListTrendTrail() {
        EventTraceQuery eventTraceQuery = new EventTraceQuery();
        PageInfo<EventTraceListDTO> pageInfo = new PageInfo<>(Collections.emptyList());
        Mockito.when(eventTraceRepository.listTrendTrail(eventTraceQuery)).thenReturn(pageInfo);
        Assert.assertEquals(pageInfo, eventTraceDomain.listTrendTrail(eventTraceQuery));
    }

    @Test
    public void testTraceTemplateByEventCode() {
        EventTraceListDTO eventTraceListDTO = new EventTraceListDTO();
        Mockito.when(eventTraceRepository.traceTemplateByEventCode("1")).thenReturn(eventTraceListDTO);
        Assert.assertEquals(eventTraceListDTO, eventTraceDomain.traceTemplateByEventCode("1"));
    }

    @Test
    public void testInsertEventTrace() {
        EventTraceParam eventTraceParam = new EventTraceParam();
        Mockito.doNothing().when(eventTraceRepository)
            .insertEventTrace(EventTraceConvertor.INSTANCE.paramToBO(eventTraceParam));
        eventTraceDomain.insertEventTrace(eventTraceParam);
    }

    @Test
    public void testListEventNameCode() {
        List<PullDownListDTO> pullDownListDTOS = Collections.emptyList();
        Mockito.when(eventTraceRepository.listEventNameCode()).thenReturn(pullDownListDTOS);
        Assert.assertEquals(pullDownListDTOS, eventTraceDomain.listEventNameCode());
    }
}
