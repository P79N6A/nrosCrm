package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.event;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 会员消费事件节点
 * 
 * @author PQ
 * @date 2019/6/20
 */
@Component
public class MemberConsumeEventNodeHandler extends BaseEventNodeHandler {
    private transient Logger logger = LoggerFactory.getLogger(BaseEventNodeHandler.class);

    /**
     * 判断事件是否能触发--事件节点外部调用
     *
     * @param defineDTO
     * @param eventTriggerHistoryParam
     * @return true or false
     * @throws BusiException
     */
    @Override
    public boolean canTrigger(MarketingDefineDTO defineDTO, EventTriggerHistoryParam eventTriggerHistoryParam)
        throws BusiException {
        return judgeConsumeEvent(defineDTO, eventTriggerHistoryParam);
    }

    // 判断是否满足消费事件
    public boolean judgeConsumeEvent(MarketingDefineDTO defineDTO, EventTriggerHistoryParam eventTriggerHistoryParam) {
        if (Objects.isNull(eventTriggerHistoryParam) || Objects.isNull(eventTriggerHistoryParam.getExtInfo())) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-MARKET-0004");
        }
        JSONObject extInfo = eventTriggerHistoryParam.getExtInfo();
        // 消费事件订单号
        String orderCode = extInfo.getString("orderCode");
        // 订单实际支付金额，单位分
        // Long orderFinalFee = extInfo.getLong("orderFinalFee");
        Long orderFinalFee = extInfo.getLong("orderFee");
        if (StringUtils.isEmpty(orderCode) || orderFinalFee == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-MARKET-0015");
        }

        // 营销流程定义中事件节点JSON
        JSONObject eventNodeJson = JSONObject.parseArray(defineDTO.getExcuteConfigJson()).getJSONObject(0);
        JSONObject eventNodeParam = eventNodeJson.getJSONObject("param");
        // 是否首单？ --当前版本不处理该场景
        // String isFirst = eventNodeParam.getString("isFirst");
        // 实际消费金额
        String factConsumeMoney = eventNodeParam.getString("factConsumeMoney");
        // 累计消费金额
        String factConsumeMoneyTotal = eventNodeParam.getString("factConsumeMoneyTotal");
        // 不存在消费金额参数定义时，抛错
        if (StringUtils.isEmpty(factConsumeMoney) && StringUtils.isEmpty(factConsumeMoneyTotal)) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-MARKET-0015");
        }

        // JS引擎
        ScriptEngineManager sc = new ScriptEngineManager();
        ScriptEngine engine = sc.getEngineByName("js");
        // 单次消费金额判断
        if (!StringUtils.isEmpty(factConsumeMoney)) {
            try {
                String[] evalArray = factConsumeMoney.split(",");
                if (evalArray.length > 1) {
                    // 对区间值取并集
                    String evalObj1 = orderFinalFee + evalArray[0];
                    String evalObj2 = orderFinalFee + evalArray[1];
                    return ((boolean) engine.eval(evalObj1)) && ((boolean) engine.eval(evalObj2));
                }
                else {
                    return (boolean) engine.eval(orderFinalFee + factConsumeMoney);
                }
            }
            catch (Exception e) {
                logger.info("MemberConsumeBaseEventFlowNodeHandler.judgeConsumeEvent() error! msg=[{}]!",
                    e.getMessage());
                return false;
            }
        }

        // 累计消费金额判断
        return this.totalConsumeJudge(factConsumeMoneyTotal, orderCode);
    }

    // 累计消费 判断 消费事件是否能执行
    public boolean totalConsumeJudge(String factConsumeMoneyTotal, String orderCode) {
        // TODO: @PQ -- 待订单中心提供接口做逻辑处理
        // logger.info("MemberConsumeBaseEventFlowNodeHandler.judgeConsumeEvent() false! 不满足累计消费金额!");
        return true;
    }

}
