package com.ztesoft.zsmart.nros.crm.core.client.api;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.CampaignServiceDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SignCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SignCampaignDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignStartOrStopParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveSignCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveCampaignServiceParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignInListQuery;

/**
 * @author fan.chaolin
 * @date 2019/4/12
 */
public interface SignCampaignService {

    /**
     * 修改报名活动
     * 
     * @param saveSignCampaignParam
     * @return
     */
    Long modifySignCampaign(SaveSignCampaignParam saveSignCampaignParam);

    /**
     * 多条件查询报名活动列表
     * 
     * @param signCampaignQuery
     * @return
     */
    PageInfo<SignCampaignDTO> queryList(SignCampaignQuery signCampaignQuery);

    /**
     * 新增报名活动
     * 
     * @param saveInviteCampaignParam
     * @return
     */
    Long createSignCampaign(SaveSignCampaignParam saveInviteCampaignParam);

    /**
     * 通过活动Id查询活动详情
     * 
     * @param campaignId
     * @return
     */
    SignCampaignDetailDTO querySignCampaignDetailById(Long campaignId);

    /**
     * 查询报名和签到用户列表
     * 
     * @param signInListQuery
     * @return
     */
    PageInfo<CampaignServiceDTO> querySignList(SignInListQuery signInListQuery);

    /**
     * 报名活动报名接口
     * 
     * @param saveCampaignServiceParam
     * @return
     */
    CampaignServiceDTO signUp(SaveCampaignServiceParam saveCampaignServiceParam);

    /**
     * 报名活动签到接口
     * 
     * @param saveCampaignServiceParam
     * @return
     */
    CampaignServiceDTO signIn(SaveCampaignServiceParam saveCampaignServiceParam);

    /**
     * 获取报名活动编辑页面参数
     * 
     * @param id 活动id
     * @return
     */
    SignCampaignDTO getSignCampaign(Long id);

    /**
     * 启用或暂停报名活动
     * 
     * @param param
     * @return
     */
    Long modifyCampaignState(CampaignStartOrStopParam param);

    /**
     * 根据主键删除
     * 
     * @param id
     * @return
     */
    Long deleteSignCampaign(Long id);
}
