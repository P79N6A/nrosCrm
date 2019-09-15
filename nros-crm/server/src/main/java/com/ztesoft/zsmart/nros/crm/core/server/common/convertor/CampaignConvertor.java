package com.ztesoft.zsmart.nros.crm.core.server.common.convertor;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.convertor.IConvertor;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveInviteCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignServiceDO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.CampaignBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhou.xiaofeng
 * @description 活动转换类
 * @date 2019-06-03
 */
@Mapper
public interface CampaignConvertor
    extends IConvertor<SaveInviteCampaignParam, Object, InviteCampaignDTO, CampaignBO, CampaignDO> {
    CampaignConvertor INSTANCE = Mappers.getMapper(CampaignConvertor.class);

    /**
     * DOPage转DTOPage
     *
     * @param serviceDoPageInfo
     * @return
     */
    PageInfo<InviteDetailDTO> doPageToDTO(PageInfo<CampaignServiceDO> serviceDoPageInfo);

    /**
     * DOPage转DTOPage
     * 
     * @param campaignDoPageInfo
     * @return
     */
    PageInfo<CampaignDO> doPageToCampaignDTO(PageInfo<CampaignDO> campaignDoPageInfo);
}
