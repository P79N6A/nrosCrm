package com.ztesoft.zsmart.nros.crm.core.server.service.impl;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.client.api.EventTraceService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTraceListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.PullDownListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTraceQuery;
import com.ztesoft.zsmart.nros.crm.core.server.domain.eventTrace.EventTraceDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员轨迹服务
 *
 * @author litao
 * @date 2019/6/12
 */
@Service
public class EventTraceServiceImpl implements EventTraceService {

    @Autowired
    private EventTraceDomain eventTraceDomain;

    @Override
    public PageInfo<EventTraceListDTO> listTrendTrail(EventTraceQuery eventTraceQuery) {
        return eventTraceDomain.listTrendTrail(eventTraceQuery);
    }

    @Override
    public PageInfo<EventTraceListDTO> queryGrowthRecordList(EventTraceQuery eventTraceQuery) {
        return eventTraceDomain.queryGrowthRecordList(eventTraceQuery);
    }

    @Override
    public List<PullDownListDTO> listEventNameCode() {
        return eventTraceDomain.listEventNameCode();
    }
}
