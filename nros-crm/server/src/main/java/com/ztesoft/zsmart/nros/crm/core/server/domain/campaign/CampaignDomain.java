/**
 * [Product]
 * crm
 * [Copyright]
 * Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 * CouponServiceImpl.java
 * [History]
 * Version  Date      Author     Content
 * -------- --------- ---------- ------------------------
 * 1.0.0    2019年3月27日   zhouyl5    最初版本
 */
package com.ztesoft.zsmart.nros.crm.core.server.domain.campaign;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.base.util.ConvertUtil;
import com.ztesoft.zsmart.nros.common.model.enums.StatusEnum;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.CampaignServiceDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignEditDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SignCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SignCampaignDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignDeleteParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignFeedBackParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignStartOrStopParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveCampaignServiceParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveInviteCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveSignCampaignParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.QueryInviteCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignInListQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.CampaignConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.CampaignDeleteConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.CampaignServiceConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.CampaignStartConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.SignCampaignConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.AuditStatusEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.CampaignTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.YesOrNoEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.util.InviteCampaignTranslator;
import com.ztesoft.zsmart.nros.crm.core.server.common.util.NrosPreconditions;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignRewardDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignServiceDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignRewardMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.CampaignDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.CampaignRewardDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.CampaignBO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.CampaignServiceBO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.SignCampaignBO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.bean.CampaignRewardBean;
import com.ztesoft.zsmart.nros.crm.core.server.repository.CampaignRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 活动领域
 *
 * @author wangzhe
 * @date 2019/4/9 13:28
 */
@Component
public class CampaignDomain {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(CampaignDomain.class);

    /**
     * 参数校验工具类
     */
    private static NrosPreconditions nrosPreconditions = NrosPreconditions.getInstance();
    /**
     * 活动Mapper
     */
    @Autowired
    private CampaignDOMapper campaignDOMapper;

    /**
     * 活动奖励mapper
     */
    @Autowired
    private CampaignRewardDOMapper campaignRewardDOMapper;

    /**
     * 活动奖励自定义mapper
     */
    @Autowired
    private CampaignRewardMapper campaignRewardMapper;

    /**
     * 活动仓储
     */
    @Autowired
    private CampaignRepository campaignRepository;

    /**
     * 通过名称查询邀请活动
     *
     * @param queryInviteCampaignQuery
     * @return
     */
    public PageInfo<InviteCampaignDTO> listInviteActivityByName(QueryInviteCampaignQuery queryInviteCampaignQuery) {

        PageHelper.startPage(queryInviteCampaignQuery.getPageIndex(), queryInviteCampaignQuery.getPageSize());
        List<CampaignDO> campaignDOList = campaignRepository.listInviteActivityByName(queryInviteCampaignQuery);
        if (org.apache.commons.collections.CollectionUtils.isEmpty(campaignDOList)) {
            return new PageInfo<>(Collections.emptyList());
        }
        PageInfo<CampaignDO> campaignDOPageInfo = new PageInfo<>(campaignDOList);


        List<InviteCampaignDTO> inviteCampaignDTOList = new ArrayList<>(campaignDOList.size());
        //转DTO
        for (CampaignDO campaignDO : campaignDOList) {
            InviteCampaignDTO campaignDTO = CampaignConvertor.INSTANCE.doToDTO(campaignDO);
            campaignDTO.setCampaignStateName(AuditStatusEnum.getName(campaignDO.getCampaignState()));
            inviteCampaignDTOList.add(campaignDTO);
        }
        PageInfo<InviteCampaignDTO> inviteCampaignPageInfo = new PageInfo<>(inviteCampaignDTOList);
        inviteCampaignPageInfo.setTotal(campaignDOPageInfo.getTotal());
        inviteCampaignPageInfo.setPageSize(campaignDOPageInfo.getPageSize());
        inviteCampaignPageInfo.setPageNum(campaignDOPageInfo.getPageNum());
        inviteCampaignPageInfo.setSize(campaignDOPageInfo.getSize());

        return inviteCampaignPageInfo;
    }

    /**
     * 创建邀请活动
     *
     * @param campaignParam
     * @return
     */
    public Long createInviteCampaign(SaveInviteCampaignParam campaignParam) {

        //基本参数校验
        basicParamCheck(campaignParam);
        //参数转BO
        CampaignBO campaignBO = InviteCampaignTranslator.translatorToBO(campaignParam);

        //补充数据:编码,创建时间,活动状态,数据状态等
//        String campaignId = GenerateCodeUtil.getFixLenthString(6);
        campaignBO.setCampaignState(AuditStatusEnum.INPROCESS.getState());
        campaignBO.setStatus(StatusEnum.ENABLE.getState());

        //保存
        return campaignRepository.createCampaign(campaignBO);

    }

    /**
     * 更新邀请活动
     *
     * @param campaignParam
     * @return
     */
    public Long updateInviteCampaign(SaveInviteCampaignParam campaignParam) {
        //基本参数校验
        basicParamCheck(campaignParam);
        //参数转BO
        CampaignBO campaignBO = InviteCampaignTranslator.translatorToBO(campaignParam);

        //更新数据
        return campaignRepository.updateCampaign(campaignBO);

    }

    /**
     * 启用停用活动
     *
     * @param startOrStopParam
     * @return
     */
    public Long startOrStopCampaign(CampaignStartOrStopParam startOrStopParam) {
        //参数转BO
        CampaignBO campaignBO = CampaignStartConvertor.INSTANCE.paramToBO(startOrStopParam);

        //更新状态
        campaignBO.setCampaignState(startOrStopParam.getCampaignState());

        //更新数据
        return campaignRepository.updateCampaign(campaignBO);
    }

    /**
     * 更新邀请活动信息
     *
     * @param saveInviteCampaignParam
     */
    public void updateInviteActivity(SaveInviteCampaignParam saveInviteCampaignParam) {
        basicParamCheck(saveInviteCampaignParam);
        CampaignDO campaignDO = ConvertUtil.beanCopy(saveInviteCampaignParam, CampaignDO.class);
        CampaignRewardDO queryDO = new CampaignRewardDO();
        queryDO.setCampaignId(saveInviteCampaignParam.getId());
        CampaignRewardDO campaignReward = campaignRewardMapper.selectByCampaignId(queryDO);
        CampaignRewardDO campaignRewardDO = ConvertUtil.beanCopy(saveInviteCampaignParam, CampaignRewardDO.class);
        if (campaignReward != null) {
            campaignRewardDO.setId(campaignReward.getId());
            campaignRewardDO.setCreator(null);
            campaignRewardDOMapper.updateByPrimaryKeySelective(campaignRewardDO);

        }
        campaignDO.setCreator(null);
        campaignDOMapper.updateByPrimaryKeySelective(campaignDO);
    }


    /**
     * 邀请活动 邀请明细列表查询
     *
     * @param queryInviteCampaignQuery
     * @return PageInfo<InviteDetailDTO>
     */
    public PageInfo<InviteDetailDTO> listDetail(QueryInviteCampaignQuery queryInviteCampaignQuery) {
        if (queryInviteCampaignQuery.getCampaignId() == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0014");
        }

        PageInfo<CampaignServiceDO> campaignServiceDOPageInfo = campaignRepository.listDetail(queryInviteCampaignQuery);
        PageInfo<InviteDetailDTO> inviteCampaignDetailPageInfo = CampaignConvertor.INSTANCE.doPageToDTO(campaignServiceDOPageInfo);

        return inviteCampaignDetailPageInfo;
    }

    /**
     * 邀请活动详情查询
     *
     * @param campaignId id
     * @return InviteCampaignDetailDTO
     */
    public InviteCampaignDetailDTO queryInviteCampaignDetailById(Long campaignId) {
        if (campaignId == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0014");
        }
        InviteCampaignDetailDTO campaignDetailDTO = campaignRepository.queryInviteCampaignDetailById(campaignId);

        return campaignDetailDTO;
    }

    /**
     * 编辑邀请活动 信息查询
     *
     * @param campaignId
     * @return
     */
    public InviteCampaignEditDTO queryEditDetailById(Long campaignId) {
        return campaignRepository.queryEditDetailById(campaignId);
    }

    public InviteCampaignDTO shareCampaignById(Long campaignId) {
        if (campaignId == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0014");
        }
        InviteCampaignDTO campaignDTO = campaignRepository.shareCampaignById(campaignId);
        return campaignDTO;

    }

    /**
     * 删除邀请活动  通过活动ID删除
     *
     * @param campaignDeleteParam
     */
    public void deleteCampaign(CampaignDeleteParam campaignDeleteParam) {
        if (campaignDeleteParam.getId() == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0014");
        }
        CampaignBO campaignBO = CampaignDeleteConvertor.INSTANCE.paramToBO(campaignDeleteParam);
        //将数据设置为无效状态
        campaignBO.setStatus(StatusEnum.DISABLE.getState());
        campaignBO.setGmtModified(new Date());
        //删除活动奖励
        CampaignRewardBean campaignReward = new CampaignRewardBean();
        campaignReward.setCampaignId(campaignBO.getId());
        campaignReward.setStatus(StatusEnum.DISABLE.getState());
        campaignBO.setCampaignReward(campaignReward);
        campaignRepository.updateCampaign(campaignBO);
    }

    /**
     * 活动点击量记录
     * @param feedBackParam
     * @return
     */
    public Long addFeedBackRecord(CampaignFeedBackParam feedBackParam) {

        //参数转BO
        CampaignBO campaignBO = InviteCampaignTranslator.translateFeedBackParamToBO(feedBackParam);

        //更新数据
        return campaignRepository.addFeedBackRecord(campaignBO);

    }


    /**
     * 基本信息非空校验
     *
     * @param saveInviteCampaignParam
     */
    private void basicParamCheck(SaveInviteCampaignParam saveInviteCampaignParam) {
        if (StringUtils.isEmpty(saveInviteCampaignParam.getName())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0011");

        }
        if (StringUtils.isEmpty(saveInviteCampaignParam.getIsLongTermActivity())) {
            if (saveInviteCampaignParam.getStartTime() == null || saveInviteCampaignParam.getEndTime() == null) {
                ExceptionHandler.publish("NROS-SBC-PROMOTION-0012");
            }
        }
        if (StringUtils.isEmpty(saveInviteCampaignParam.getRichDetail())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0013");
        }
    }

    /**
     * 保存报名活动入参校验
     *
     * @param saveSignCampaignParam
     */
    private void checkSaveSignCampaignParam(SaveSignCampaignParam saveSignCampaignParam) {
        if (StringUtils.isEmpty(saveSignCampaignParam.getName())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0011");
        }

        if (StringUtils.isEmpty(saveSignCampaignParam.getRichPic())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0021");
        }

        if (StringUtils.isEmpty(saveSignCampaignParam.getLocation())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0022");
        }

        if (StringUtils.isEmpty(saveSignCampaignParam.getRichDetail())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0023");
        }

        if (StringUtils.isEmpty(saveSignCampaignParam.getContactPhone())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0024");
        }

        if (StringUtils.isEmpty(saveSignCampaignParam.getButtonText())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0026");
        }

        if (StringUtils.isEmpty(saveSignCampaignParam.getSubmitTitle())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0025");
        }

        if (StringUtils.isEmpty(saveSignCampaignParam.getSubmitDescription())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0027");
        }

        if (saveSignCampaignParam.getStartTime() == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0012");
        }

        if (saveSignCampaignParam.getEndTime() == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0012");
        }

        if (saveSignCampaignParam.getSignStartTime() == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0028");
        }

        if (saveSignCampaignParam.getSignEndTime() == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0028");
        }

    }

    /**
     * 报名、签到入参校验
     * @param saveCampaignServiceParam
     */
    public void checkCampaignServiceParam(SaveCampaignServiceParam saveCampaignServiceParam) {
        //参数校验
        if (saveCampaignServiceParam.getCampaignId() == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0014");
        }
        if (StringUtils.isEmpty(saveCampaignServiceParam.getSignName())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0036");
        }
        if (StringUtils.isEmpty(saveCampaignServiceParam.getSignPhone())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0037");
        }
    }

    /**
     * 修改报名活动信息
     *
     * @param saveSignCampaignParam
     * @return
     */
    public Long modifySignCampaign(SaveSignCampaignParam saveSignCampaignParam) {
        // 数据校验
        checkSaveSignCampaignParam(saveSignCampaignParam);
        Long campaignId = saveSignCampaignParam.getId();
        if (campaignId == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0014");
        }

        // Param转BO
        SignCampaignBO signCampaignBO = SignCampaignConvertor.INSTANCE.paramToBO(saveSignCampaignParam);
        return campaignRepository.modifySignCampaign(signCampaignBO);
    }


    /**
     * 多条件查询报名活动列表
     * @param signCampaignQuery
     * @return
     */
    public PageInfo<SignCampaignDTO> signCampaignQueryList(SignCampaignQuery signCampaignQuery) {
        PageInfo<SignCampaignDTO> signCampaignDTOList = campaignRepository.signCampaignQueryList(signCampaignQuery);
        return signCampaignDTOList;
    }

    /**
     * 新增报名活动
     * @param saveSignCampaignParam
     * @return
     */
    public Long createSignCampaign(SaveSignCampaignParam saveSignCampaignParam) {
        //参数校验
        checkSaveSignCampaignParam(saveSignCampaignParam);

        saveSignCampaignParam.setCampaignType(CampaignTypeEnum.SIGN.getValue());
        saveSignCampaignParam.setCampaignState(AuditStatusEnum.DESIGNING.getState());
        saveSignCampaignParam.setStatus(StatusEnum.ENABLE.getState());
        //Param转SignCampaignBO
        SignCampaignBO signCampaignBO = SignCampaignConvertor.INSTANCE.paramToBO(saveSignCampaignParam);
        Long campaignId = campaignRepository.createSignCampaign(signCampaignBO);
        campaignRepository.saveCampaignStatisticRecord(campaignId);
        return campaignId;
    }

    /**
     * 通过活动id查询
     *
     * @param campaignId
     * @return
     */
    public SignCampaignDetailDTO querySignCampaignDetailById(Long campaignId) {
        //参数校验
        NrosPreconditions.getInstance().notNull(campaignId, "id is null");
        SignCampaignDetailDTO signCampaignDetailDTO = campaignRepository.querySignCampaignDetailById(campaignId);
        return signCampaignDetailDTO;
    }

    /**
     * 查询报名用户列表
     * @param signInListQuery
     * @return
     */
    public PageInfo<CampaignServiceDTO> querySignList(SignInListQuery signInListQuery) {
        //参数校验
        NrosPreconditions.getInstance().notNull(signInListQuery.getCampaignId(), "id is null");
        PageInfo<CampaignServiceDTO> pageInfo = campaignRepository.querySignList(signInListQuery);
        return pageInfo;
    }

    /**
     * 用户报名
     * @param saveCampaignServiceParam
     * @return
     */
    public CampaignServiceDTO signUp(SaveCampaignServiceParam saveCampaignServiceParam) {
        //参数校验
        checkCampaignServiceParam(saveCampaignServiceParam);
        //验证用户是否已经报名
        checkIsSignUp(saveCampaignServiceParam);

        String isSignInOpen = campaignRepository.isSignInOpen(saveCampaignServiceParam.getCampaignId());
        if (isSignInOpen.equals(YesOrNoEnum.YES.getValue())) {
            String signInCode = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
            saveCampaignServiceParam.setSignInCode(signInCode);
        }
        CampaignServiceBO campaignServiceBO = CampaignServiceConvertor.INSTANCE.paramToBO(saveCampaignServiceParam);
        campaignServiceBO.setSignTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        campaignServiceBO.setIsSignIn(YesOrNoEnum.NO.getValue());
        CampaignServiceDTO campaignServiceDTO = campaignRepository.saveCampaignServiceRecord(campaignServiceBO);
        campaignRepository.updateSignUpStatistic(campaignServiceDTO.getCampaignId());
        return campaignServiceDTO;
    }

    /**
     * 校验报名用户是否已经报名
     * @param saveCampaignServiceParam
     */
    private void checkIsSignUp(SaveCampaignServiceParam saveCampaignServiceParam) {
        String signPhone = saveCampaignServiceParam.getSignPhone();
        Long campaignId = saveCampaignServiceParam.getCampaignId();
        CampaignServiceDO campaignServiceDO = campaignRepository.selectSignPhone(campaignId, signPhone);
        if (campaignServiceDO != null) {
            throw new BusiException("用户已经报名");
        }
    }

    /**
     * 用户签到
     * @param saveCampaignServiceParam
     * @return
     */
    public CampaignServiceDTO signIn(SaveCampaignServiceParam saveCampaignServiceParam) {
        //参数校验
        checkCampaignServiceParam(saveCampaignServiceParam);
        String signPhone = saveCampaignServiceParam.getSignPhone();
        Long campaignId = saveCampaignServiceParam.getCampaignId();
        CampaignServiceDO campaignServiceDO = campaignRepository.selectSignPhone(campaignId, signPhone);
        if (campaignServiceDO == null) {
            throw new BusiException("尚未报名");
        }

        String isSignInOpen = campaignRepository.isSignInOpen(saveCampaignServiceParam.getCampaignId());
        if (isSignInOpen.equals(YesOrNoEnum.YES.getValue())) {
            if (StringUtils.isEmpty(saveCampaignServiceParam.getSignInCode())) {
                ExceptionHandler.publish("NROS-SBC-PROMOTION-0029");
            }
            else {
                if (!saveCampaignServiceParam.getSignInCode().equals(campaignServiceDO.getSignInCode())) {
                    throw new BusiException("签到码错误");
                }
            }
        }
        campaignServiceDO.setSignInTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        campaignServiceDO.setIsSignIn(YesOrNoEnum.YES.getValue());
        CampaignServiceDTO campaignServiceDTO = campaignRepository.updateSignInStatus(campaignServiceDO);
        campaignRepository.updateSignInStatistic(campaignServiceDTO.getCampaignId());
        return campaignServiceDTO;
    }

    /**
     * 通过id获取报名活动编辑页面参数
     * @param id 活动id
     * @return
     */
    public SignCampaignDTO getSignCampaign(Long id) {
        //参数校验
        if (id == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-0014");
        }
        return campaignRepository.getSignCampaign(id);
    }

    /**
     * 删除报名活动
     * @param id 活动id
     * @return
     */
    public Long deleteSignCampaign(Long id) {
        return campaignRepository.deleteSignCampaign(id);
    }
}
