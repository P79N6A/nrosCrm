package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.base.util.DateUtil;
import com.ztesoft.zsmart.nros.base.util.SnowflakeIdWorker;
import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.base.util.StringUtil;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import com.ztesoft.zsmart.nros.crm.core.server.common.constant.PromotionContants;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.ProtectDateTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.ValidDateTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums.MarketingNodeExecuteTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.BaseFlowNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.MemberServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.PointUpdateParams;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 执行赠送积分动作
 *
 * @author admin
 * @date 2018/3/3
 **/
@Component
public class SendPointActionHandler extends BaseFlowNodeHandler {

    private static final String IDENTIFY_CODE = "CRM";

    private static Logger logger = LoggerFactory.getLogger(SendPointActionHandler.class);

    public void handler(MarketingDefineDTO marketingDefineDTO, String json,
                        EventTriggerHistoryParam eventTriggerHistoryParam, List<TargetUserDTO> targetUserListYes, String marketingInstanceId) {
        try {
            String merchantCode = marketingDefineDTO.getMerchantCode();

            JSONObject actionParamJson = JSON.parseObject(json);
            // 变化的积分
            String exchangePoints = actionParamJson.getString("points");
            // 积分保护期类型
            String protectDateType = actionParamJson.getString("protectDateType");
            // 有效期类型
            String validDateType = actionParamJson.getString("validDateType");

            if (Objects.isNull(exchangePoints) || Objects.isNull(protectDateType) || Objects.isNull(validDateType)) {
                logger.error("SendPointActionHandler.handler() error:营销流程赠送积分动作节点参数配置不正确!");
                return;
            }

            // 计算积分变化时间
            Date exchangeTime;
            // 积分生效日期：立即生效
            if (ProtectDateTypeEnum.RIGHTNOW.getValue().equals(protectDateType)) {
                exchangeTime = DateUtil.getNow();
            }
            else {
                exchangeTime = DateUtil.getDateZero(new Date(), actionParamJson.getInteger("protectFixTerm"));
            }

            // 计算积分失效时间 （默认永久有效）
            final Date invalidTime;
            if (ValidDateTypeEnum.FIXTERM.getValue().equals(validDateType)) {
                // 失效日期：领取后指定天数
                invalidTime = DateUtil.getDateZero(new Date(), actionParamJson.getInteger("invalidFixTerm"));
            }
            else if (ValidDateTypeEnum.INVALIDTIME.getValue().equals(validDateType)) {
                invalidTime = DateUtil.getDate(
                        LocalDate.now().plusYears(actionParamJson.getLong("receiveYear")).getYear(),
                        DateUtil.getDayOfMonth(new Date()), Integer.parseInt(DateUtil.getCurrentDay()));
            }
            else {
                invalidTime = null;
            }

            // 组装接口参数
            List<PointUpdateParams> pointUpdateParamsList = Lists.newArrayList();
            targetUserListYes.stream().filter(memberDetailDTO -> StringUtil.isNotNull(memberDetailDTO.getPhone()))
                    .forEach(memberDetailDTO -> {
                        PointUpdateParams pointUpdateParams = new PointUpdateParams();
                        pointUpdateParams.setMemberId(memberDetailDTO.getId());
                        pointUpdateParams.setMerchantCode(merchantCode);
                        // TODO 操作人改为json类型，暂时屏蔽
                        pointUpdateParams.setPoint(Integer.valueOf(exchangePoints));
                        pointUpdateParams.setChannel(IDENTIFY_CODE);
                        pointUpdateParams.setDescription(PromotionContants.POINTS_REASON_NAME_MARKETING);
                        pointUpdateParams.setEffDate(exchangeTime);
                        pointUpdateParams.setExpDate(invalidTime);

                        pointUpdateParams.setBizOrder(this.generateBizOrder(memberDetailDTO.getId(), marketingInstanceId));
                        pointUpdateParamsList.add(pointUpdateParams);
                    });

            logger.info("begin to call MemberProxy.updatePointRecord……");
            // 调用批量赠送积分接口
            MemberServiceImpl memberService = SpringContextUtils.getBean(MemberServiceImpl.class);
            ResponseMsg responseMsg = memberService.batchUpdatePointRecord(pointUpdateParamsList);
            logger.info("MemberProxy.updatePointRecord result:###[code={},message={}]###", responseMsg.getCode(),
                    responseMsg.getMessage());
        }
        catch (Exception e) {
            logger.error("SendPointActionHandler.handler error! msg={} ", e.getMessage());
        }

    }

    /**
     * 生成bizOrder，新增积分需要
     *
     * @return
     */
    private String generateBizOrder(Long memberId, String marketingInstanceId) {
//        int bizOrderLen = 64;
//        String dateTime = DateUtil.getTimestamp();
//        StringBuilder sb = new StringBuilder(marketingInstanceId);
//        sb.append(memberId.toString()).append(dateTime);
//        String bizOrder = sb.toString();
//
//        if (bizOrder.length() > bizOrderLen) {
//            bizOrder = bizOrder.substring(0, bizOrderLen - 1);
//        }
//        return bizOrder;

        StringBuilder sb = new StringBuilder(memberId.toString());
        sb.append(SnowflakeIdWorker.generateId().toString());
        String bizOrder = sb.toString();
        return bizOrder;
    }

    @Override
    public void doBusiness(DelegateExecution delegateExecution, String nodeJsonParam) {
        MarketingDefineDTO marketingDefineDTO = new MarketingDefineDTO();
        marketingDefineDTO.setId((Long) delegateExecution.getVariable("marketingDefineId"));
        marketingDefineDTO.setMarketingType((String) delegateExecution.getVariable("marketingType"));
        marketingDefineDTO.setMerchantCode((String) delegateExecution.getVariable("merchantCode"));

        String marketingInstanceId = delegateExecution.getProcessInstanceId();

        // String nodeJsonParam = (String) param.getValue(delegateExecution);
        EventTriggerHistoryParam eventTriggerHistoryParam = (EventTriggerHistoryParam) delegateExecution
                .getVariable("msgContext");
        List<TargetUserDTO> targetUserListYes = (List<TargetUserDTO>) delegateExecution
                .getVariable("targetUserListYes");

        this.handler(marketingDefineDTO, nodeJsonParam, eventTriggerHistoryParam, targetUserListYes, marketingInstanceId);
    }

    @Override
    public String getNodeType() {
        return MarketingNodeExecuteTypeEnum.SENDPOINT.getCode();
    }
}
