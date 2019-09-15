package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action;

import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineQuery;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingStatusEnum;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.MarketingDomain;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.BaseFlowNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils.BpmnModelUtil;

/**
 * 委托部署动作：专门用于在指定时间点部署目标流程
 *
 * @author PQ
 * @date 2019/7/17
 */
@Component
public class DelegateDeployActionHandler extends BaseFlowNodeHandler {
    private static Logger logger = LoggerFactory.getLogger(DelegateDeployActionHandler.class);

    @Override
    public void doBusiness(DelegateExecution delegateExecution, String nodeJsonParam) {
        this.handler(nodeJsonParam);
    }

    public void handler(String nodeJsonParam) {
        JSONObject actionParamObj = JSON.parseObject(nodeJsonParam);
        // 流程定义ID
        Long marketingDefineId = actionParamObj.getLong("marketingDefineId");
        if (null == marketingDefineId) {
            logger.error("DelegateDeployActionHandler.handler()……marketingDefineId is empty!");
            return;
        }
        // 查询目标流程定义
        MarketingDomain marketingDomain = SpringContextUtils.getBean(MarketingDomain.class);
        MarketingDefineQuery marketingDefineQuery = new MarketingDefineQuery();
        marketingDefineQuery.setId(marketingDefineId);
        MarketingDefineDTO marketingDefineDTO = marketingDomain.detail(marketingDefineQuery);
        if (null == marketingDefineDTO || null == marketingDefineDTO.getMarketingConfigJson()) {
            logger.error("DelegateDeployActionHandler.handler()……target MarketingDefine:{} is null!",
                marketingDefineId);
            return;
        }
        // 只有在目标流程定义为启用状态时，才给与部署
        if (MarketingStatusEnum.ENABLE.getState().equals(marketingDefineDTO.getMarketingStatus())) {
            // 部署目标流程定义
            BpmnModelUtil.deployProcess(marketingDefineDTO.getMarketingConfigJson(), marketingDefineId);
        }
    }
}
