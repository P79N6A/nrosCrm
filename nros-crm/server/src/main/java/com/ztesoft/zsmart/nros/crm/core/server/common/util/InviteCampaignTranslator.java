package com.ztesoft.zsmart.nros.crm.core.server.common.util;

import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.base.util.ConvertUtil;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignFeedBackParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveInviteCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.CampaignConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignServiceDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignStatisticDO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.CampaignBO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.bean.CampaignFeedbackBean;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.bean.CampaignRewardBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动转换类
 * @date 2019-04-15
 */
public class InviteCampaignTranslator {

    /**
     * 邀请活动详情DTO转换
     *
     * @param campaignDO
     * @param statisticDO
     * @param campaignServiceDOList
     * @return
     */
    public static InviteCampaignDetailDTO translatorToDTO(CampaignDO campaignDO, CampaignStatisticDO statisticDO,
                                                          List<CampaignServiceDO> campaignServiceDOList) {

        if (campaignDO == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0016");
        }
        InviteCampaignDetailDTO campaignDetailDTO = ConvertUtil.beanCopy(campaignDO, InviteCampaignDetailDTO.class);

        if (statisticDO != null) {
            campaignDetailDTO.setClickCounter(statisticDO.getClickCounter());
            campaignDetailDTO.setPageviewCounter(statisticDO.getPageviewCounter());
            campaignDetailDTO.setRegisterNum(statisticDO.getRegisterNum());
            campaignDetailDTO.setRecommandNum(statisticDO.getRecommandNum());
        }

        if (!campaignServiceDOList.isEmpty()) {
            List<InviteDetailDTO> inviteDetailList = new ArrayList<>();
            campaignServiceDOList.forEach(
                    campaignServiceDO -> {
                        InviteDetailDTO detail = ConvertUtil.beanCopy(campaignServiceDO, InviteDetailDTO.class);
                        inviteDetailList.add(detail);
                    }
            );
            campaignDetailDTO.setInviteDetailList(inviteDetailList);
        }
        return campaignDetailDTO;
    }

    /**
     * 保存参数转活动BO
     *
     * @param campaignParam
     * @return
     */
    public static CampaignBO translatorToBO(SaveInviteCampaignParam campaignParam) {

        CampaignRewardBean campaignReward = ConvertUtil.beanCopy(campaignParam, CampaignRewardBean.class);
        CampaignBO campaignBO = CampaignConvertor.INSTANCE.paramToBO(campaignParam);
        campaignBO.setCampaignReward(campaignReward);
        return campaignBO;
    }

    /**
     * 活动反馈参数转BO
     *
     * @return
     */
    public static CampaignBO translateFeedBackParamToBO(CampaignFeedBackParam feedBackParam) {
        CampaignBO campaignBO = new CampaignBO();
        campaignBO.setId(feedBackParam.getCampaignId());
        CampaignFeedbackBean campaignFeedback = ConvertUtil.beanCopy(feedBackParam, CampaignFeedbackBean.class);
        campaignBO.setCampaignFeedback(campaignFeedback);
        return campaignBO;
    }
}
