package com.ztesoft.zsmart.nros.crm.core.server.common.convertor;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.convertor.IConvertor;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SignCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveSignCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignDO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.SignCampaignBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * @author yangkai
 * @descrition 报名活动转换类
 * @date 2019-06-10
 */
@Mapper
public interface SignCampaignConvertor
    extends IConvertor<SaveSignCampaignParam, SignCampaignQuery, SignCampaignDTO, SignCampaignBO, CampaignDO> {
    SignCampaignConvertor INSTANCE = Mappers.getMapper(SignCampaignConvertor.class);

    /**
     * DOList转DTOList
     * 
     * @param campaignDOList
     * @return 报名活动列表
     */
    List<SignCampaignDTO> doDOListToDTO(List<CampaignDO> campaignDOList);

    /**
     * doPage转dtoPage
     * 
     * @param campaignDOPageInfo
     * @return 报名活动列表页面
     */
    PageInfo<SignCampaignDTO> doPageToDTO(PageInfo<CampaignDO> campaignDOPageInfo);
}
