package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.event;

import java.util.Date;
import java.util.List;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums.MarketingNodeExecuteTypeEnum;
import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.crm.core.client.api.MarketingService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTriggerHistoryDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingFrequenceTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingFrequenceUnitEnum;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.BaseFlowNodeHandler;

/**
 * 营销流程定义事件节点
 * 
 * @author PQ
 * @date 2019/4/19
 */
// @Component
public abstract class BaseEventNodeHandler extends BaseFlowNodeHandler {
    // public abstract class BaseEventNodeHandler implements JavaDelegate {
    private Logger logger = LoggerFactory.getLogger(BaseEventNodeHandler.class);

    @Override
    public void doBusiness(DelegateExecution delegateExecution, String param) {
        EventTriggerHistoryParam eventTriggerHistoryParam = (EventTriggerHistoryParam) delegateExecution
            .getVariable("msgContext");
        // 将事件触发用户设置为目标执行用户
        TargetUserDTO memberDetailDTO = new TargetUserDTO();
        memberDetailDTO.setId(eventTriggerHistoryParam.getMemberId());
        memberDetailDTO.setName(eventTriggerHistoryParam.getMemberName());
        memberDetailDTO.setPhone(eventTriggerHistoryParam.getMemberPhone());
        List<TargetUserDTO> targetUserListYes = Lists.newArrayList(memberDetailDTO);
        delegateExecution.setVariable("targetUserListYes", targetUserListYes);
    }

    /**
     * 根据事件节点中的param校验触发用户是否满足条件.<br>
     * 
     * @param defineDTO
     * @param eventTriggerHistoryParam
     * @return
     * @throws BusiException
     */
    public Boolean execute(MarketingDefineDTO defineDTO, EventTriggerHistoryParam eventTriggerHistoryParam)
        throws BusiException {
        logger.info("执行事件节点...{}", defineDTO.getId());
        // 判断是否满足触发条件
        if (!judgeTriggerFrequence(defineDTO, eventTriggerHistoryParam)
            || !canTrigger(defineDTO, eventTriggerHistoryParam)) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否满足事件触发条件
     * 
     * @param defineDTO
     * @param eventTriggerHistoryParam
     * @return boolean
     * @author PQ
     * @throws BusiException
     */
    public abstract boolean canTrigger(MarketingDefineDTO defineDTO, EventTriggerHistoryParam eventTriggerHistoryParam)
        throws BusiException;

    /**
     * 判断是否满足触发频率:允许继续触发返回true，否则返回false
     *
     * @param defineDTO
     * @param eventTriggerHistoryParam
     * @return boolean
     * @author PQ
     * @date 2019/4/29
     */
    public boolean judgeTriggerFrequence(MarketingDefineDTO defineDTO,
        EventTriggerHistoryParam eventTriggerHistoryParam) {
        // 不限制触发频率，直接返回true
        if (MarketingFrequenceTypeEnum.NOLIMIT.getValue().equals(defineDTO.getFrequenceType())) {
            return true;
        }

        // 根据触发会员ID && 触发营销事件定义ID，查询事件触发历史记录表，看是否存在触发记录
        Long marketingId = defineDTO.getId();
        String merchantCode = eventTriggerHistoryParam.getMerchantCode();
        Long memberId = eventTriggerHistoryParam.getMemberId();
        MarketingService marketingService = SpringContextUtils.getBean(MarketingService.class);
        List<EventTriggerHistoryDTO> eventTriggerHistoryDtoList = marketingService
            .queryTriggerHisByUserAndMarketingId(merchantCode, marketingId, memberId);

        // 会员在当前营销活动定义中没有过触发记录，直接返回true
        if (CollectionUtils.isEmpty(eventTriggerHistoryDtoList)) {
            return true;
        }

        EventTriggerHistoryDTO eventTriggerHistoryDTO = eventTriggerHistoryDtoList.get(0);
        // 触发频率限制为：每个用户只能触发一次
        if (MarketingFrequenceTypeEnum.ONLYONE.getValue().equals(defineDTO.getFrequenceType())) {
            logger.warn(
                "BaseEventNodeHandler ##### this user:[{}],merchantId:[{}] had trigger marketingDefine:[{}] once!",
                memberId, merchantCode, marketingId);
            return false;
        }

        String frequenceUnit = defineDTO.getFrequenceUnit();
        // 触发频率限制为：每个用户在一段时间内仅触发一次
        if (MarketingFrequenceTypeEnum.ONEINRANGE.getValue().equals(defineDTO.getFrequenceType())
            && null != defineDTO.getFrequenceCount() && StringUtils.isNotBlank(frequenceUnit)) {
            // 日区间
            if (MarketingFrequenceUnitEnum.DAY.getValue().equals(frequenceUnit)) {
                Date addDays = DateUtils.addDays(eventTriggerHistoryDTO.getGmtCreate(), defineDTO.getFrequenceCount());
                if (addDays.before(new Date())) {
                    return true;
                }
            }
            // 周区间
            else if (MarketingFrequenceUnitEnum.WEEK.getValue().equals(frequenceUnit)) {
                Date addWeeks = DateUtils.addWeeks(eventTriggerHistoryDTO.getGmtCreate(),
                    defineDTO.getFrequenceCount());
                if (addWeeks.before(new Date())) {
                    return true;
                }
            }
            // 月区间
            else if (MarketingFrequenceUnitEnum.MONTH.getValue().equals(frequenceUnit)) {
                Date addMonths = DateUtils.addMonths(eventTriggerHistoryDTO.getGmtCreate(),
                    defineDTO.getFrequenceCount());
                if (addMonths.before(new Date())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public String getNodeType() {
        return MarketingNodeExecuteTypeEnum.FILTER.getCode();
    }

}
