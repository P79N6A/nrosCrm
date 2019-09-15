package com.ztesoft.zsmart.nros.crm.core.server.common.convertor;

import com.ztesoft.zsmart.nros.base.convertor.IConvertor;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignStartOrStopParam;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignDO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.CampaignBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhou.xiaofeng
 * @description 活动启用停用转换类
 * @date 2019-06-03
 */
@Mapper
public interface CampaignStartConvertor extends IConvertor<CampaignStartOrStopParam, Object, Object, CampaignBO, CampaignDO> {
    CampaignStartConvertor INSTANCE = Mappers.getMapper(CampaignStartConvertor.class);

    /**
     * DO转BO
     * @param campaignDO
     * @return
     */
    CampaignDO boToDo(CampaignDO campaignDO);
}
