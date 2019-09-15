package com.ztesoft.zsmart.nros.crm.core.server.domain.eventTrace;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTraceListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.PullDownListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTraceParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTraceQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.EventTraceConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.repository.EventTraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 轨迹事件domain
 *
 * @author litao
 * @date 2019/6/12
 */
@Component
public class EventTraceDomain {

    @Autowired
    private EventTraceRepository eventTraceRepository;

    /**
     * 获取会员事件轨迹列表
     *
     * @param eventTraceQuery
     * @return
     */
    public PageInfo<EventTraceListDTO> listTrendTrail(EventTraceQuery eventTraceQuery) {
        return eventTraceRepository.listTrendTrail(eventTraceQuery);
    }

    public PageInfo<EventTraceListDTO> queryGrowthRecordList(EventTraceQuery eventTraceQuery) {
        return eventTraceRepository.queryGrowthRecordList(eventTraceQuery);
    }

    /**
     * 通过事件code获取模板信息
     *
     * @param eventCode
     * @return
     */
    public EventTraceListDTO traceTemplateByEventCode(String eventCode) {
        return eventTraceRepository.traceTemplateByEventCode(eventCode);
    }

    /**
     * 新增会员轨迹
     *
     * @param eventTraceParam
     */
    public void insertEventTrace(EventTraceParam eventTraceParam) {
        eventTraceRepository.insertEventTrace(EventTraceConvertor.INSTANCE.paramToBO(eventTraceParam));
    }

    /**
     * 获取事件种类下拉列表
     *
     * @return
     */
    public List<PullDownListDTO> listEventNameCode() {
        return eventTraceRepository.listEventNameCode();
    }
}
