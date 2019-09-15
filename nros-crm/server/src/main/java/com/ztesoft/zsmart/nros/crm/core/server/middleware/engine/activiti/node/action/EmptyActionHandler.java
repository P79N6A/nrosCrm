package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action;

import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.base.util.StringUtil;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineQuery;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.MarketingDomain;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.BaseFlowNodeHandler;

/**
 * 执行空节点动作
 *
 * @author admin
 * @date 2018/5/14
 **/
@Component
public class EmptyActionHandler extends BaseFlowNodeHandler {
    private static Logger logger = LoggerFactory.getLogger(EmptyActionHandler.class);

    @Override
    public void doBusiness(DelegateExecution delegateExecution, String nodeJsonParam) {
        // 如果流程定义相关参数为空，则自动赋值，用于适配activiti自启动的周期性触发流程场景
        if (null == delegateExecution.getVariable("marketingType")
            || null == delegateExecution.getVariable("marketingDefineId")) {
            try {
                Long marketingDefineId = null;
                String marketingType = null;
                String merchantCode = null;
                if (StringUtil.isNotNull(nodeJsonParam)) {
                    JSONObject actionParamObj = JSON.parseObject(nodeJsonParam);
                    marketingDefineId = actionParamObj.getLong("marketingDefineId");
                    if (null != marketingDefineId) {
                        MarketingDomain marketingDomain = SpringContextUtils.getBean(MarketingDomain.class);
                        MarketingDefineQuery marketingDefineQuery = new MarketingDefineQuery();
                        marketingDefineQuery.setId(marketingDefineId);
                        MarketingDefineDTO marketingDefineDTO = marketingDomain.detail(marketingDefineQuery);
                        if (null != marketingDefineDTO) {
                            marketingType = marketingDefineDTO.getMarketingType();
                            merchantCode = marketingDefineDTO.getMerchantCode();
                        }
                    }
                }
                if (null != marketingType && null != marketingDefineId) {
                    delegateExecution.setVariable("marketingDefineId", marketingDefineId);
                    delegateExecution.setVariable("marketingType", marketingType);
                    delegateExecution.setVariable("merchantCode", merchantCode);
                }
                else {
                    throw new BusiException("the marketingDefine info related to current Activiti:{} is null!",
                        delegateExecution.getProcessInstanceId());
                }
            }
            catch (Exception e) {
                logger.error("EmptyActionHandler.doBusiness() error! msg={}", e.getMessage());
                ExceptionHandler.publish("NROS-SBC-PROMOTION-MARKET-0002");
            }
        }
    }
}
