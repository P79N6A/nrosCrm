package com.ztesoft.zsmart.nros.crm.core.server.repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTraceListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.PullDownListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTraceQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.EventTraceConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTraceDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTraceTemplateDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.EventTraceMapper;
import com.ztesoft.zsmart.nros.crm.core.server.domain.eventTrace.model.EventTraceBO;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会员事件轨迹Repository
 *
 * @author litao
 * @date 2019/6/13
 */
@Repository
@Setter
public class EventTraceRepository {

    @Autowired
    private EventTraceMapper eventTraceMapper;

    private Logger logger = LoggerFactory.getLogger(EventTraceRepository.class);

    /**
     * 获取会员事件轨迹列表
     *
     * @param eventTraceQuery
     * @return
     */
    public PageInfo<EventTraceListDTO> listTrendTrail(EventTraceQuery eventTraceQuery) {
        logger.info("start to repository listTrendTrail...");

        EventTraceDO eventTraceDO = new EventTraceDO();
        if (StringUtils.isNotBlank(eventTraceQuery.getEventCode())) {
            Long eventId = eventTraceMapper.getEventIdByEventCode(eventTraceQuery.getEventCode());
            eventTraceDO.setEventId(eventId);
        }
        PageHelper.startPage(eventTraceQuery.getPageIndex(), eventTraceQuery.getPageSize());
        eventTraceDO.setLang(eventTraceQuery.getLang());
        eventTraceDO.setMemberId(eventTraceQuery.getMemberId());
        List<EventTraceDO> eventTraceLists = eventTraceMapper.listTrendTrail(eventTraceDO);
        PageInfo<EventTraceDO> eventTraceDOPageInfo = new PageInfo<>(eventTraceLists);
        return EventTraceConvertor.INSTANCE.doPageToDTO(eventTraceDOPageInfo);
    }

    /**
     * 查询成长值记录
     *
     * @param eventTraceQuery
     * @return
     */
    public PageInfo<EventTraceListDTO> queryGrowthRecordList(EventTraceQuery eventTraceQuery) {
        PageHelper.startPage(eventTraceQuery.getPageIndex(), eventTraceQuery.getPageSize());

        EventTraceDO eventTraceDO = new EventTraceDO();
        eventTraceDO.setLang(eventTraceQuery.getLang());
        eventTraceDO.setMemberId(eventTraceQuery.getMemberId());
        List<EventTraceDO> eventTraceLists = eventTraceMapper.queryGrowthRecordList(eventTraceDO);
        PageInfo<EventTraceDO> eventTraceDOPageInfo = new PageInfo<>(eventTraceLists);
        return EventTraceConvertor.INSTANCE.doPageToDTO(eventTraceDOPageInfo);
    }


    /**
     * 通过事件code获取模板信息
     *
     * @param eventCode
     * @return
     */
    public EventTraceListDTO traceTemplateByEventCode(String eventCode) {
        logger.info("start to repository traceTemplateByEventCode...");
        EventTraceListDTO eventTraceListDTO = new EventTraceListDTO();
        EventTraceTemplateDO eventTraceTemplateDO = eventTraceMapper.traceTemplateByEventCode(eventCode, "zh");
        eventTraceListDTO.setEventId(eventTraceTemplateDO.getEventId());
        eventTraceListDTO.setContent(eventTraceTemplateDO.getTemplateContent());
        return eventTraceListDTO;
    }

    /**
     * 新增会员轨迹
     *
     * @param eventTraceBO
     */
    public void insertEventTrace(EventTraceBO eventTraceBO) {
        logger.info("start to repository insertEventTrace...");
        eventTraceMapper.insertEventTrace(EventTraceConvertor.INSTANCE.boToDO(eventTraceBO));
    }

    /**
     * 获取事件种类下拉列表
     *
     * @return
     */
    public List<PullDownListDTO> listEventNameCode() {
        logger.info("start to repository listEventNameCode...");
        return eventTraceMapper.listEventNameCode();
    }

}
