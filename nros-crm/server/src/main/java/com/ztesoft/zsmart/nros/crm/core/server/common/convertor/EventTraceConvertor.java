package com.ztesoft.zsmart.nros.crm.core.server.common.convertor;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.convertor.IConvertor;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTraceListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTraceParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTraceQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTraceDO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.eventTrace.model.EventTraceBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 事件轨迹转换类
 * @author litao
 */
@Mapper
public interface EventTraceConvertor extends IConvertor<EventTraceParam, EventTraceQuery, EventTraceListDTO, EventTraceBO, EventTraceDO> {
    EventTraceConvertor INSTANCE = Mappers.getMapper(EventTraceConvertor.class);

    /**
     * DO转DTO
     * @param eventTraceDOPageInfo
     * @return
     */
    PageInfo<EventTraceListDTO> doPageToDTO(PageInfo<EventTraceDO> eventTraceDOPageInfo);

    /**
     * dto转param
     * @param eventTraceListDTO
     * @return
     */
    EventTraceParam dtoToParam(EventTraceListDTO eventTraceListDTO);
}
