package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper;

import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignDO;

import java.util.List;

/**
 * 报名活动
 *
 * @author fan.chaolin
 * @date 2019/4/13
 */
public interface SignCampaignMapper {

    /**
     * 查询报名活动列表
     * @param signCampaignQuery
     * @return
     */
    List<CampaignDO> queryList(SignCampaignQuery signCampaignQuery);

    /**
     * 查询报名活动是否开启签到码
     * @param campaignId
     * @return
     */
    String queryIsSignInOpen(Long campaignId);
}
