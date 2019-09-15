package com.ztesoft.zsmart.nros.crm.core.server.service.impl;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.client.api.SignCampaignService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.CampaignServiceDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SignCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SignCampaignDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignStartOrStopParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveCampaignServiceParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveSignCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignInListQuery;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.CampaignDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 报名活动服务
 *
 * @author fan.chaolin
 * @date 2019/4/12
 */
@Service
public class SignCampaignServiceImpl implements SignCampaignService {
    private static final Logger logger = LoggerFactory.getLogger(SignCampaignServiceImpl.class);

    @Autowired
    private CampaignDomain campaignDomain;

    /**
     * 修改报名活动信息
     *
     * @param saveSignCampaignParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long modifySignCampaign(SaveSignCampaignParam saveSignCampaignParam) {
        return campaignDomain.modifySignCampaign(saveSignCampaignParam);
    }

    /**
     * 多条件查询报名活动列表
     *
     * @param signCampaignQuery
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<SignCampaignDTO> queryList(SignCampaignQuery signCampaignQuery) {
        PageInfo<SignCampaignDTO> campaignDOPageInfo = campaignDomain.signCampaignQueryList(signCampaignQuery);
        return campaignDOPageInfo;
    }

    /**
     * 新增报名活动
     * @param saveInviteCampaignParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createSignCampaign(SaveSignCampaignParam saveInviteCampaignParam) {
        return campaignDomain.createSignCampaign(saveInviteCampaignParam);
    }

    /**
     * 查询活动详情
     * @param campaignId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SignCampaignDetailDTO querySignCampaignDetailById(Long campaignId) {
        return campaignDomain.querySignCampaignDetailById(campaignId);
    }

    /**
     * 查询报名、签到用户列表
     * @param signInListQuery
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<CampaignServiceDTO> querySignList(SignInListQuery signInListQuery) {
        PageInfo pageInfo = campaignDomain.querySignList(signInListQuery);
        return pageInfo;
    }

    /**
     * 用户报名
     * @param saveCampaignServiceParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CampaignServiceDTO signUp(SaveCampaignServiceParam saveCampaignServiceParam) {
        return campaignDomain.signUp(saveCampaignServiceParam);
    }

    /**
     * 用户签到
     * @param saveCampaignServiceParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CampaignServiceDTO signIn(SaveCampaignServiceParam saveCampaignServiceParam) {
        return campaignDomain.signIn(saveCampaignServiceParam);
    }

    /**
     * 获取报名活动参数
     * @param id 活动id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SignCampaignDTO getSignCampaign(Long id) {
        SignCampaignDTO signCampaignDTO = campaignDomain.getSignCampaign(id);
        return signCampaignDTO;
    }

    /**
     * 修改活动状态
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long modifyCampaignState(CampaignStartOrStopParam param) {
        return campaignDomain.startOrStopCampaign(param);
    }

    /**
     * 删除报名活动
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteSignCampaign(Long id) {
        return campaignDomain.deleteSignCampaign(id);
    }
}
