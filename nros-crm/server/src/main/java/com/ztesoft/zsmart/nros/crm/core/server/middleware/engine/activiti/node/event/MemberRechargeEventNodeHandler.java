package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.event;

import com.alibaba.fastjson.JSONObject;
import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Objects;

/**
 * 会员充值触发事件节点
 */
@Component
public class MemberRechargeEventNodeHandler extends BaseEventNodeHandler {

    private Logger logger = LoggerFactory.getLogger(MemberRechargeEventNodeHandler.class);

    /**
     * 判断充值事件是否能触发
     *
     * @param defineDTO
     * @param eventTriggerHistoryParam
     * @return
     * @throws BusiException
     */
    @Override
    public boolean canTrigger(MarketingDefineDTO defineDTO, EventTriggerHistoryParam eventTriggerHistoryParam)
        throws BusiException {
        return judgeRechargeEvent(defineDTO, eventTriggerHistoryParam);
    }

    private boolean judgeRechargeEvent(MarketingDefineDTO defineDTO,
        EventTriggerHistoryParam eventTriggerHistoryParam) {
        if (Objects.isNull(eventTriggerHistoryParam) || Objects.isNull(eventTriggerHistoryParam.getExtInfo())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-MARKET-0004");
        }
        JSONObject extInfo = eventTriggerHistoryParam.getExtInfo();
        // 订单号
        String orderCode = extInfo.getString("orderCode");
        // 充值金额，单位分
        Long rechargeMoney = extInfo.getLong("rechargeMoney");
        if (StringUtils.isEmpty(orderCode) || rechargeMoney == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-MARKET-0015");
        }

        // 营销流程定义中事件节点JSON
        JSONObject eventNodeJson = JSONObject.parseArray(defineDTO.getExcuteConfigJson()).getJSONObject(0);
        JSONObject eventNodeParam = eventNodeJson.getJSONObject("param");
        String factRechargeMoney = eventNodeParam.getString("factConsumeMoney");

        logger.info("mq推送的充值金额为{}，前端传递的触发金额为{}", rechargeMoney, factRechargeMoney);

        // JS引擎
        ScriptEngineManager sc = new ScriptEngineManager();
        ScriptEngine engine = sc.getEngineByName("js");
        // 单次消费金额判断
        if (!StringUtils.isEmpty(factRechargeMoney)) {
            try {
                String[] evalArray = factRechargeMoney.split(",");
                if (evalArray.length > 1) {
                    // 对区间值取并集
                    String evalObj1 = rechargeMoney + evalArray[0];
                    String evalObj2 = rechargeMoney + evalArray[1];
                    return ((boolean) engine.eval(evalObj1)) && ((boolean) engine.eval(evalObj2));
                }
                else {
                    return (boolean) engine.eval(rechargeMoney + factRechargeMoney);
                }
            }
            catch (Exception e) {
                logger.info("MemberRechargeEventNodeHandler.judgeRechargeEvent() error! msg=[{}]!", e.getMessage());
                return false;
            }
        }

        return true;
    }

}
