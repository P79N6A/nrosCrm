package com.ztesoft.zsmart.nros.crm.core.server.common.convertor;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.convertor.IConvertor;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTriggerHistoryDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTriggerHistoryQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.EventTriggerHistoryDO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.model.EventTriggerHistoryBO;

/**
 * 事件触发历史记录表转换类
 * 
 * @author PQ
 * @date 2019/6/24
 */
@Mapper
public interface EventTriggerHistoryConvertor extends
    IConvertor<EventTriggerHistoryParam, EventTriggerHistoryQuery, EventTriggerHistoryDTO, EventTriggerHistoryBO, EventTriggerHistoryDO> {
    EventTriggerHistoryConvertor INSTANCE = Mappers.getMapper(EventTriggerHistoryConvertor.class);

    /**
     * DO转DTO
     *
     * @param eventTriggerHistoryDOPageInfo
     * @return
     */
    PageInfo<EventTriggerHistoryDTO> doPageToDTO(PageInfo<EventTriggerHistoryDO> eventTriggerHistoryDOPageInfo);

    /**
     * DOList转DTOList
     * 
     * @param eventTriggerHistoryDOList
     * @return
     */
    List<EventTriggerHistoryDTO> doDOListToDTO(List<EventTriggerHistoryDO> eventTriggerHistoryDOList);
}
