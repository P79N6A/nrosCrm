package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action;

import java.util.List;
import java.util.stream.Collectors;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums.MarketingNodeExecuteTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.BaseFlowNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.CouponService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.CouponBatchSendParam;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.MemberIdNameParam;

/**
 * 执行赠送优惠券动作
 *
 * @author admin
 * @date 2018/5/14
 **/
@Component
public class SendCouponActionHandler extends BaseFlowNodeHandler {
    // public class SendCouponActionHandler implements JavaDelegate {

    private static Logger logger = LoggerFactory.getLogger(SendCouponActionHandler.class);

    public void handler(MarketingDefineDTO marketingDefineDTO, String json, List<TargetUserDTO> targetUserListYes,
        String marketingInstanceId, String activityNodeId) {
        try {
            // logger.info("SendCouponActionHandler.nodeJsonParam=:{}", json);
            JSONObject actionParamObj = JSON.parseObject(json);
            String couponCode = actionParamObj.getString("couponCode");
            if (StringUtils.isEmpty(couponCode)) {
                logger.warn("SendCouponActionHandler.handler error! couponCode is empty!!");
                return;
            }
            List<MemberIdNameParam> memberIdNameList = targetUserListYes.stream().parallel().map(userItem -> {
                MemberIdNameParam memberIdNameParam = new MemberIdNameParam();
                memberIdNameParam.setMemberId(userItem.getId());
                memberIdNameParam.setMemberName(userItem.getName());
                return memberIdNameParam;
            }).collect(Collectors.toList());
            // 拼接参数
            CouponBatchSendParam couponParam = new CouponBatchSendParam();
            // couponParam.setMerchantCode(marketingDefineDTO.getMerchantCode());
            couponParam.setCouponCode(couponCode);
            couponParam.setReceiveType("0");
            // 流程定义ID
            couponParam.setActiveCode(String.valueOf(marketingDefineDTO.getId()));
            // 流程实例ID
            couponParam.setActiveInstanceCode(marketingInstanceId);
            // 流程节点ID
            couponParam.setActiveNodeCode(activityNodeId);
            couponParam.setMemberIdNameList(memberIdNameList);
            // 调用赠送优惠券接口
            CouponService couponService = SpringContextUtils.getBean(CouponService.class);
            couponService.batchSend(couponParam);
        }
        catch (Exception e) {
            logger.info("SendCouponActionHandler.handler error! msg={} ", e.getMessage());
        }

    }

    @Override
    public void doBusiness(DelegateExecution delegateExecution, String nodeJsonParam) {
        MarketingDefineDTO marketingDefineDTO = new MarketingDefineDTO();
        marketingDefineDTO.setId((Long) delegateExecution.getVariable("marketingDefineId"));
        marketingDefineDTO.setMarketingType((String) delegateExecution.getVariable("marketingType"));
        marketingDefineDTO.setMerchantCode((String) delegateExecution.getVariable("merchantCode"));
        String marketingInstanceId = delegateExecution.getProcessInstanceId();
        String activityNodeId = delegateExecution.getCurrentActivityId();

        List<TargetUserDTO> targetUserListYes = (List<TargetUserDTO>) delegateExecution
            .getVariable("targetUserListYes");
        this.handler(marketingDefineDTO, nodeJsonParam, targetUserListYes, marketingInstanceId, activityNodeId);
    }

    @Override
    public String getNodeType() {
        return MarketingNodeExecuteTypeEnum.SENDCOUPON.getCode();
    }
}
