package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper;

import com.ztesoft.zsmart.nros.crm.core.client.model.dto.PullDownListDTO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTraceDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTraceTemplateDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 轨迹事件mapper
 */
public interface EventTraceMapper {

    /**
     * 通过操作类型获取事件id
     * @param eventCode 操作类型
     * @return
     */
    Long getEventIdByEventCode(@Param("eventCode") String eventCode);

    /**
     * 查询会员事件轨迹列表
     * @param eventTraceDO
     * @return
     */
    List<EventTraceDO> listTrendTrail(EventTraceDO eventTraceDO);


    List<EventTraceDO> queryGrowthRecordList(EventTraceDO eventTraceDO);

    /**
     * 根据事件code查询模板内容及事件id
     * @param eventCode
     * @param lang
     * @return
     */
    EventTraceTemplateDO traceTemplateByEventCode(@Param("eventCode") String eventCode, @Param("lang") String lang);

    /**
     * 新增会员轨迹
     *
     * @param eventTraceDO
     */
    void insertEventTrace(EventTraceDO eventTraceDO);

    /**
     * 获取事件种类下拉列表
     * @return
     */
    List<PullDownListDTO> listEventNameCode();
}
