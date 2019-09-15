package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingProcessFinishStatusEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.MarketingDomain;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.BaseFlowNodeHandler;

/**
 * 流程执行完成触发动作
 * 
 * @author PQ
 * @date 2019/6/24
 */
@Component
public class FlowFinishActionHandler extends BaseFlowNodeHandler {
    @Override
    public void doBusiness(DelegateExecution delegateExecution, String nodeJsonParam) {
        String marketingType = (String) delegateExecution.getVariable("marketingType");
        String merchantCode = (String) delegateExecution.getVariable("merchantCode");
        Long eventTriggerHistoryId = (Long) delegateExecution.getVariable("eventTriggerHistoryId");
        // 当流程定义为事件营销流程时，更新事件触发历史记录表状态字段为完成
        if (MarketingTypeEnum.EVENT.getCode().equals(marketingType) && null != eventTriggerHistoryId) {
            MarketingDomain marketingDomain = SpringContextUtils.getBean(MarketingDomain.class);
            EventTriggerHistoryParam eventTriggerHistoryParam = new EventTriggerHistoryParam();
            eventTriggerHistoryParam.setMerchantCode(merchantCode);
            eventTriggerHistoryParam.setId(eventTriggerHistoryId);
            // 设置完成状态
            eventTriggerHistoryParam.setIsProcessFinished(MarketingProcessFinishStatusEnum.YES.getValue());
            marketingDomain.updateEventTriggerHisByKey(eventTriggerHistoryParam);
        }

    }
}
