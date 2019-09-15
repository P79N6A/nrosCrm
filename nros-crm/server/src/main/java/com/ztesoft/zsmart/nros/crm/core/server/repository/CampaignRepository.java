package com.ztesoft.zsmart.nros.crm.core.server.repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.util.ConvertUtil;
import com.ztesoft.zsmart.nros.common.model.enums.StatusEnum;
import com.ztesoft.zsmart.nros.core.repository.BaseRepository;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.CampaignReserveTimeDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.CampaignServiceDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.InviteCampaignEditDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SignCampaignDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.SignCampaignDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignReserveTimeParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.QueryInviteCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignCampaignQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.SignInListQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.CampaignConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.CampaignServiceConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.SignCampaignConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.AuditStatusEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.CampaignTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.DataStateEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.util.InviteCampaignTranslator;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignFeedbackDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignReserveTimeDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignRewardDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignServiceDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignStatisticDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignFeedbackMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignReserveTimeMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignRewardMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignServiceMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignStatisticMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.InviteCampaignMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.SignCampaignMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.CampaignDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.CampaignFeedbackDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.CampaignReserveTimeDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.CampaignRewardDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.CampaignServiceDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.CampaignStatisticDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.CampaignDomain;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.CampaignBO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.CampaignServiceBO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.SignCampaignBO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.campaign.model.bean.CampaignRewardBean;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 活动仓储 demo
 *
 * @author wangzhe
 * @date 2019/6/1 14:38
 */
@Repository
@Setter
public class CampaignRepository implements BaseRepository {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(CampaignDomain.class);

    @Autowired
    private CampaignDOMapper campaignDOMapper;

    @Autowired
    private CampaignFeedbackDOMapper campaignFeedbackDOMapper;

    @Autowired
    private CampaignReserveTimeDOMapper campaignReserveTimeDOMapper;

    @Autowired
    private CampaignServiceDOMapper campaignServiceDOMapper;

    @Autowired
    private CampaignStatisticDOMapper campaignStatisticDOMapper;

    @Autowired
    private CampaignFeedbackMapper campaignFeedbackMapper;

    @Autowired
    private CampaignRewardDOMapper campaignRewardDOMapper;

    @Autowired
    private CampaignRewardMapper campaignRewardMapper;

    @Autowired
    private SignCampaignMapper signCampaignMapper;

    /**
     * 邀请活动自定义mapper
     */
    @Autowired
    private InviteCampaignMapper inviteCampaignMapper;

    /**
     * 活动明细自定义mapper
     */
    @Autowired
    private CampaignServiceMapper campaignServiceMapper;

    /**
     * 预约活动时间自定义接口
     */
    @Autowired
    private CampaignReserveTimeMapper campaignReserveTimeMapper;

    /**
     * 活动统计自定义mapper
     */
    @Autowired
    private CampaignStatisticMapper campaignStatisticMapper;

    /**
     * 查询活动
     *
     * @param queryInviteCampaignQuery
     * @return
     */
    public List<CampaignDO> listInviteActivityByName(QueryInviteCampaignQuery queryInviteCampaignQuery) {
        CampaignDO campaignDO = ConvertUtil.beanCopy(queryInviteCampaignQuery, CampaignDO.class);
        return inviteCampaignMapper.listInviteActivityByName(campaignDO);
    }

    /**
     * 创建活动
     *
     * @param campaignBO
     * @return
     */
    public Long createCampaign(CampaignBO campaignBO) {
        logger.info("createCampaign request come in**********");
        //BO转DO
        CampaignDO campaignDO = CampaignConvertor.INSTANCE.boToDO(campaignBO);
        CampaignRewardDO campaignRewardDO = ConvertUtil.beanCopy(campaignBO.getCampaignReward(), CampaignRewardDO.class);
        int returnId = campaignDOMapper.insert(campaignDO);
        //补齐活动ID
        campaignRewardDO.setCampaignId(campaignDO.getId());
        logger.info("活动ID*****" + campaignDO.getId() + "*********返回ID*******" + returnId);
        campaignRewardDO.setStatus(StatusEnum.ENABLE.getState());
        //插入数据库
        campaignRewardDOMapper.insert(campaignRewardDO);
        return campaignDO.getId();
    }

    /**
     * 更新活动
     *
     * @param campaignBO
     * @return
     */
    public Long updateCampaign(CampaignBO campaignBO) {
        CampaignDO campaignDO = CampaignConvertor.INSTANCE.boToDO(campaignBO);
        CampaignRewardDO campaignRewardDO = ConvertUtil.beanCopy(campaignBO.getCampaignReward(), CampaignRewardDO.class);

        //更新数据库
        campaignDOMapper.updateByPrimaryKeySelective(campaignDO);
        campaignRewardDO.setCampaignId(campaignDO.getId());
        CampaignRewardDO rewardDO = campaignRewardMapper.selectByCampaignId(campaignRewardDO);
        if (rewardDO != null) {
            campaignRewardDO.setId(rewardDO.getId());
            campaignRewardDOMapper.updateByPrimaryKeySelective(campaignRewardDO);
        }

        return campaignDO.getId();
    }


    /**
     * 邀请活动详情查询
     *
     * @param campaignId
     * @return
     */
    public InviteCampaignDetailDTO queryInviteCampaignDetailById(Long campaignId) {
        CampaignDO campaignDO = campaignDOMapper.selectByPrimaryKey(campaignId);
        CampaignStatisticDO statisticDO = new CampaignStatisticDO();
        statisticDO.setCampaignId(campaignId);
        CampaignStatisticDO campaignStatisticResult = campaignStatisticMapper.selectByCampaignId(statisticDO);
        CampaignServiceDO campaignServiceDO = new CampaignServiceDO();
        campaignServiceDO.setCampaignId(campaignId);
        List<CampaignServiceDO> campaignServiceDOList = campaignServiceMapper.selectInviteDetail(campaignServiceDO);

        //活动反馈表统计今日访问量
        Integer toadyCounter = campaignFeedbackMapper.countToadyCounter(campaignId);

        //将查询到的DO与明细列表转DTO
        InviteCampaignDetailDTO inviteCampaignDetailDTO = InviteCampaignTranslator
                .translatorToDTO(campaignDO, campaignStatisticResult, campaignServiceDOList);
        //今日访问量
        inviteCampaignDetailDTO.setTodayCounter(toadyCounter);
        return inviteCampaignDetailDTO;
    }


    /**
     * 编辑邀请活动 信息查询
     *
     * @param campaignId
     * @return
     */
    public InviteCampaignEditDTO queryEditDetailById(Long campaignId) {
        CampaignDO campaignDO = campaignDOMapper.selectByPrimaryKey(campaignId);
        CampaignRewardDO campaignRewardDO = new CampaignRewardDO();
        campaignRewardDO.setCampaignId(campaignId);
        CampaignRewardDO campaignReward = campaignRewardMapper.selectByCampaignId(campaignRewardDO);

        //活动信息和奖励信息转DTO
        InviteCampaignEditDTO inviteCampaignEditDTO = ConvertUtil.beanCopy(campaignReward, InviteCampaignEditDTO.class);
        inviteCampaignEditDTO.setId(campaignDO.getId());
        inviteCampaignEditDTO.setName(campaignDO.getName());
        inviteCampaignEditDTO.setIsLongTermActivity(campaignDO.getIsLongTermActivity());
        inviteCampaignEditDTO.setStartTime(campaignDO.getStartTime());
        inviteCampaignEditDTO.setEndTime(campaignDO.getEndTime());
        inviteCampaignEditDTO.setRichDetail(campaignDO.getRichDetail());
        return inviteCampaignEditDTO;
    }

    /**
     * 邀请活动 邀请列表查询
     *
     * @param queryInviteCampaignQuery
     * @return
     */
    public PageInfo<CampaignServiceDO> listDetail(QueryInviteCampaignQuery queryInviteCampaignQuery) {
        // 开始分页
        PageHelper.startPage(queryInviteCampaignQuery.getPageIndex(), queryInviteCampaignQuery.getPageSize());

        List<CampaignServiceDO> campaignServiceDOList = campaignServiceMapper.selectInviteDetailByDate(queryInviteCampaignQuery);
        return new PageInfo<>(campaignServiceDOList);
    }

    /**
     * 活动点击量记录
     *
     * @param campaignBO
     * @return
     */
    public Long addFeedBackRecord(CampaignBO campaignBO) {
        CampaignFeedbackDO campaignFeedbackDO = ConvertUtil.beanCopy(campaignBO.getCampaignFeedback(), CampaignFeedbackDO.class);
        campaignFeedbackDOMapper.insert(campaignFeedbackDO);
        return campaignFeedbackDO.getId();
    }

    /**
     * 活动奖励发放
     *
     * @param rewardBO
     * @return
     */
    public Long sendReward(CampaignRewardBean rewardBO) {
        CampaignRewardDO rewardDO = ConvertUtil.beanCopy(rewardBO, CampaignRewardDO.class);
        //插入数据库
        campaignRewardDOMapper.updateByPrimaryKeySelective(rewardDO);
        return rewardDO.getId();
    }

    /**
     * 邀请活动分享
     *
     * @param campaignId
     * @return
     */
    public InviteCampaignDTO shareCampaignById(Long campaignId) {
        CampaignDO campaignDO = campaignDOMapper.selectByPrimaryKey(campaignId);
        InviteCampaignDTO campaignDTO = CampaignConvertor.INSTANCE.doToDTO(campaignDO);
        return campaignDTO;
    }

    /**
     * 报名活动列表查询
     *
     * @param signCampaignQuery
     * @return 报名活动
     */
    public PageInfo<SignCampaignDTO> signCampaignQueryList(SignCampaignQuery signCampaignQuery) {
        PageHelper.startPage(signCampaignQuery.getPageIndex(), signCampaignQuery.getPageSize());
        signCampaignQuery.setStatus(DataStateEnum.getDataState(signCampaignQuery.getStatus()));
        List<CampaignDO> campaignDOList = signCampaignMapper.queryList(signCampaignQuery);
        for (CampaignDO campaignDO : campaignDOList) {
            campaignDO.setCampaignState(AuditStatusEnum.getName(campaignDO.getCampaignState()));
        }
        PageInfo<CampaignDO> campaignDOPageInfo = new PageInfo<>(campaignDOList);
        PageInfo<SignCampaignDTO> signCampaignDTOPageInfo = SignCampaignConvertor.INSTANCE.doPageToDTO(campaignDOPageInfo);
        return signCampaignDTOPageInfo;
    }

    /**
     * 保存活动预约时间段
     *
     * @param timeParams
     * @param campaignId
     */
    private void saveCampaignReserveTime(List<CampaignReserveTimeParam> timeParams, Long campaignId) {
        if (CollectionUtils.isEmpty(timeParams) || timeParams.size() == 0) {
            logger.info("campaignReserveTimeParams is null ");
        }
        else {
            //Param转DO
            List<CampaignReserveTimeDO> campaignReserveTimeDOS = ConvertUtil.listEntity2DTO(timeParams, CampaignReserveTimeDO.class);
            for (CampaignReserveTimeDO campaignReserveTimeDO : campaignReserveTimeDOS) {
                //补充数据
                campaignReserveTimeDO.setCampaignId(campaignId);
                campaignReserveTimeDO.setStatus(StatusEnum.ENABLE.getState());
                //插入数据库
                campaignReserveTimeDOMapper.insertSelective(campaignReserveTimeDO);
            }
        }
    }

    /**
     * 报名活动新增
     *
     * @param signCampaignBO
     * @return
     */
    public Long createSignCampaign(SignCampaignBO signCampaignBO) {
        CampaignDO campaignDO = SignCampaignConvertor.INSTANCE.boToDO(signCampaignBO);
        campaignDO.setCampaignState(AuditStatusEnum.DESIGNING.getState());
        campaignDO.setCampaignType(CampaignTypeEnum.SIGN.getValue());
        campaignDO.setStatus(StatusEnum.ENABLE.getState());

        campaignDOMapper.insert(campaignDO);
        Long campaignId = campaignDO.getId();
        //新增活动时间段
        List<CampaignReserveTimeParam> timeParams = signCampaignBO.getCampaignReserveTimeParamList();
        saveCampaignReserveTime(timeParams, campaignId);
        return campaignId;
    }

    /**
     * 修改报名活动
     *
     * @param signCampaignBO
     * @return
     */
    public Long modifySignCampaign(SignCampaignBO signCampaignBO) {

        //BO对象转DO
        CampaignDO campaignDO = SignCampaignConvertor.INSTANCE.boToDO(signCampaignBO);
        campaignDOMapper.updateByPrimaryKeySelective(campaignDO);
        //取出campaignID
        Long campaignId = campaignDO.getId();
        List<CampaignReserveTimeParam> timeParams = signCampaignBO.getCampaignReserveTimeParamList();
        deleteCampaignReserveTime(campaignId);
        saveCampaignReserveTime(timeParams, campaignId);
        return campaignId;
    }

    /**
     * 通过campaignId删除
     *
     * @param campaignId
     */
    private void deleteCampaignReserveTime(Long campaignId) {
        campaignReserveTimeMapper.deleteByCampaignId(campaignId);
    }

    /**
     * 获取报名活动详情
     *
     * @param campaignId
     * @return
     */
    public SignCampaignDetailDTO querySignCampaignDetailById(Long campaignId) {
        //创建SignCampaignDetailDTO对象
        SignCampaignDetailDTO signCampaignDetailDTO = new SignCampaignDetailDTO();
        CampaignDO campaignDO = campaignDOMapper.selectByPrimaryKey(campaignId);
        CampaignStatisticDO campaignStatisticDO = campaignStatisticMapper.selectCampaignStatisticByCampaignId(campaignId);
        signCampaignDetailDTO.setId(campaignDO.getId());
        signCampaignDetailDTO.setName(campaignDO.getName());
        signCampaignDetailDTO.setCampaignId(campaignDO.getId());
        signCampaignDetailDTO.setLinkAddress(campaignDO.getLinkAddress());
        signCampaignDetailDTO.setLocation(campaignDO.getLocation());
        signCampaignDetailDTO.setStartTime(campaignDO.getStartTime());
        signCampaignDetailDTO.setEndTime(campaignDO.getEndTime());
        signCampaignDetailDTO.setSignStartTime(campaignDO.getSignStartTime());
        signCampaignDetailDTO.setSignEndTime(campaignDO.getSignEndTime());
        signCampaignDetailDTO.setRichDetail(campaignDO.getRichDetail());
        signCampaignDetailDTO.setWxPic(campaignDO.getWxPic());
        signCampaignDetailDTO.setCampaignState(campaignDO.getCampaignState());
        if (campaignStatisticDO != null) {
            signCampaignDetailDTO.setPageviewCounter(campaignStatisticDO.getPageviewCounter());
            signCampaignDetailDTO.setSignInNum(campaignStatisticDO.getSignInNum());
            signCampaignDetailDTO.setSignUpNum(campaignStatisticDO.getSignUpNum());
        }
        return signCampaignDetailDTO;
    }

    /**
     * 查询报名用户列表
     *
     * @param signInListQuery
     * @return
     */
    public PageInfo<CampaignServiceDTO> querySignList(SignInListQuery signInListQuery) {
        PageHelper.startPage(signInListQuery.getPageIndex(), signInListQuery.getPageSize());
        List<CampaignServiceDO> campaignServiceDOList = campaignServiceMapper.querySignList(signInListQuery);
        PageInfo<CampaignServiceDO> campaignServiceDOPageInfo = new PageInfo<>(campaignServiceDOList);
        PageInfo<CampaignServiceDTO> campaignServiceDTOPageInfo = CampaignServiceConvertor.INSTANCE.doPageToDTO(campaignServiceDOPageInfo);
        return campaignServiceDTOPageInfo;
    }

    /**
     * 通过手机号查询报名服务
     *
     * @param campaignId
     * @param signPhone
     * @return
     */
    public CampaignServiceDO selectSignPhone(Long campaignId, String signPhone) {
        CampaignServiceDO campaignServiceDO = new CampaignServiceDO();
        campaignServiceDO.setCampaignId(campaignId);
        campaignServiceDO.setSignPhone(signPhone);
        return campaignServiceMapper.selectBySignPhone(campaignServiceDO);
    }

    /**
     * 保存活动服务信息
     *
     * @param campaignServiceBO
     */
    public CampaignServiceDTO saveCampaignServiceRecord(CampaignServiceBO campaignServiceBO) {
        CampaignServiceDO campaignServiceDO = CampaignServiceConvertor.INSTANCE.boToDO(campaignServiceBO);
        Long id = (long) campaignServiceDOMapper.insert(campaignServiceDO);
        campaignServiceDO.setId(id);
        CampaignServiceDTO campaignServiceDTO = CampaignServiceConvertor.INSTANCE.doToDTO(campaignServiceDO);
        return campaignServiceDTO;
    }

    /**
     * 通过活动id查询是否开始开启签到码
     *
     * @param campaignId
     * @return
     */
    public String isSignInOpen(Long campaignId) {
        String isSignInOpen = signCampaignMapper.queryIsSignInOpen(campaignId);
        return isSignInOpen;
    }

    /**
     * 更新签到状态
     *
     * @param campaignServiceDO
     * @return 活动服务DTO
     */
    public CampaignServiceDTO updateSignInStatus(CampaignServiceDO campaignServiceDO) {
        campaignServiceDOMapper.updateByPrimaryKeySelective(campaignServiceDO);
        CampaignServiceDTO campaignServiceDTO = CampaignServiceConvertor.INSTANCE.doToDTO(campaignServiceDO);
        return campaignServiceDTO;
    }

    /**
     * 更新报名统计信息
     *
     * @param campaignId
     */
    public void updateSignUpStatistic(Long campaignId) {
        campaignStatisticMapper.updateSignUpStatistic(campaignId);
    }

    /**
     * 更新签到统计信息
     *
     * @param campaignId
     */
    public void updateSignInStatistic(Long campaignId) {
        campaignStatisticMapper.updateSignInStatistic(campaignId);
    }

    /**
     * 通过报名id获取报名活动DTO
     *
     * @param id 活动id
     * @return
     */
    public SignCampaignDTO getSignCampaign(Long id) {
        CampaignDO campaignDO = campaignDOMapper.selectByPrimaryKey(id);
        SignCampaignDTO signCampaignDTO = SignCampaignConvertor.INSTANCE.doToDTO(campaignDO);
        CampaignReserveTimeDO campaignReserveTimeDO = new CampaignReserveTimeDO();
        campaignReserveTimeDO.setCampaignId(id);
        campaignReserveTimeDO.setStatus(StatusEnum.ENABLE.getState());
        List<CampaignReserveTimeDO> campaignReserveTimeDOList = campaignReserveTimeMapper.selectBycampaignId(campaignReserveTimeDO);
        if (!CollectionUtils.isEmpty(campaignReserveTimeDOList)) {
            List<CampaignReserveTimeDTO> campaignReserveTimeDTOList = ConvertUtil.listEntity2DTO(campaignReserveTimeDOList, CampaignReserveTimeDTO.class);
            signCampaignDTO.setCampaignReserveTimeDTOList(campaignReserveTimeDTOList);
        }
        return signCampaignDTO;
    }

    /**
     * 保存活动统计信息
     *
     * @param campaignId
     */
    public void saveCampaignStatisticRecord(Long campaignId) {
        CampaignStatisticDO campaignStatisticDO = new CampaignStatisticDO();
        campaignStatisticDO.setCampaignId(campaignId);
        campaignStatisticDO.setCampaignType(CampaignTypeEnum.INVITE.getValue());
        campaignStatisticDO.setStatus(StatusEnum.ENABLE.getState());
        campaignStatisticDOMapper.insert(campaignStatisticDO);
    }

    /**
     * 删除报名活动
     *
     * @param id
     * @return
     */
    public Long deleteSignCampaign(Long id) {
        CampaignDO campaignDO = new CampaignDO();
        campaignDO.setStatus(StatusEnum.DISABLE.getState());
        campaignDO.setId(id);
        campaignDOMapper.updateByPrimaryKeySelective(campaignDO);
        return id;
    }
}
