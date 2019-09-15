package com.ztesoft.zsmart.nros.crm.core.server.common.convertor;

import com.ztesoft.zsmart.nros.base.convertor.IConvertor;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignDeleteParam;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignDO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.CampaignBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhou.xiaofeng
 * @description 活动删除转换类
 * @date 2019-06-03
 */
@Mapper
public interface CampaignDeleteConvertor extends IConvertor<CampaignDeleteParam, Object, Object, CampaignBO, CampaignDO> {
    CampaignDeleteConvertor INSTANCE = Mappers.getMapper(CampaignDeleteConvertor.class);

    /**
     * DO转BO
     * @param campaignDO
     * @return
     */
    CampaignBO dotoBo(CampaignDO campaignDO);

}
