package com.ztesoft.zsmart.nros.crm.core.server.common.convertor;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.convertor.IConvertor;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.CampaignServiceDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveCampaignServiceParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignInListQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignServiceDO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.CampaignServiceBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author yangkai
 * @descrition 活动详情转换类
 * @date 2019-06-10
 */
@Mapper
public interface CampaignServiceConvertor extends
    IConvertor<SaveCampaignServiceParam, SignInListQuery, CampaignServiceDTO, CampaignServiceBO, CampaignServiceDO> {
    CampaignServiceConvertor INSTANCE = Mappers.getMapper(CampaignServiceConvertor.class);

    /**
     * DOList转DTOList
     * 
     * @param campaignServiceDOList
     * @return
     */
    List<CampaignServiceDTO> doDOListToDTO(List<CampaignServiceDO> campaignServiceDOList);

    /**
     * DOPage转DTOPage
     * 
     * @param smsTemplateDOPageInfo
     * @return 活动服务页面
     */
    PageInfo<CampaignServiceDTO> doPageToDTO(PageInfo<CampaignServiceDO> smsTemplateDOPageInfo);
}
