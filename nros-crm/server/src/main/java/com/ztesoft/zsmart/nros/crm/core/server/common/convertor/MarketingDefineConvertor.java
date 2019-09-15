package com.ztesoft.zsmart.nros.crm.core.server.common.convertor;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.convertor.IConvertor;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.MarketingDefineParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.MarketingDefineDO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.model.MarketingBO;

/**
 * 营销流程定义转换类
 * 
 * @author PQ
 * @date 2019/6/24
 */
@Mapper
public interface MarketingDefineConvertor
    extends IConvertor<MarketingDefineParam, MarketingDefineQuery, MarketingDefineDTO, MarketingBO, MarketingDefineDO> {
    MarketingDefineConvertor INSTANCE = Mappers.getMapper(MarketingDefineConvertor.class);

    /**
     * DO转DTO
     * 
     * @param marketingDefineDOPageInfo
     * @return
     */
    PageInfo<MarketingDefineDTO> doPageToDTO(PageInfo<MarketingDefineDO> marketingDefineDOPageInfo);


    /**
     * DOList转DTOList
     * @param marketingDefineDOList
     * @return
     */
    List<MarketingDefineDTO> doDOListToDTO(List<MarketingDefineDO> marketingDefineDOList);
}
