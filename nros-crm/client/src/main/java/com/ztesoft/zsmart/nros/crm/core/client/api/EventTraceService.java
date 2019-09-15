package com.ztesoft.zsmart.nros.crm.core.client.api;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTraceListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.PullDownListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTraceQuery;

import java.util.List;

/**
 * 会员动态轨迹接口
 *
 * @author litao
 * @date 2019/6/12
 */
public interface EventTraceService {

    /**
     * 获取会员轨迹列表
     *
     * @param eventTraceQuery
     * @return
     */
    PageInfo<EventTraceListDTO> listTrendTrail(EventTraceQuery eventTraceQuery);

    PageInfo<EventTraceListDTO> queryGrowthRecordList(EventTraceQuery eventTraceQuery);

    /**
     * 获取事件种类下拉列表
     *
     * @return
     */
    List<PullDownListDTO> listEventNameCode();
}
