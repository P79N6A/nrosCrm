package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.event;

import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.EventTriggerHistoryParam;
import com.ztesoft.zsmart.nros.crm.core.server.common.constant.PromotionContants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.base.exception.BusiException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 注册事件
 *
 * @author admin
 * @date 2018/6/13
 **/
@Component
public class RegisterEventNodeHandler extends BaseEventNodeHandler {

    private Logger logger = LoggerFactory.getLogger(RegisterEventNodeHandler.class);

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
        return judgeRegisterChannel(defineDTO, eventTriggerHistoryParam);
    }

    /**
     * 判断注册渠道类型是否一致
     *
     * @return
     * @throws BusiException
     */
    public boolean judgeRegisterChannel(MarketingDefineDTO defineDTO, EventTriggerHistoryParam eventTriggerHistoryParam)
        throws BusiException {
        if (eventTriggerHistoryParam == null || eventTriggerHistoryParam.getExtInfo() == null) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-MARKET-0004");
        }
        JSONObject eventNodeJson = JSONObject.parseArray(defineDTO.getExcuteConfigJson()).getJSONObject(0);
        // json 中配置的eventType
        Long configEventType = eventNodeJson.getJSONObject("param").getLong("channel");
        // 不限制注册渠道时候 返回true
        if (PromotionContants.REGISTRY_TYPE_NOLIMIT.equals(configEventType)) {
            return true;
        }
        JSONObject extInfo = eventTriggerHistoryParam.getExtInfo();
        // 事件中传递的注册渠道类型
        String registerChannel = extInfo.getString("registerChannel");
        if (StringUtils.isEmpty(registerChannel)) {
            ExceptionHandler.publish("NROS-SBC-PROMOTION-MARKET-0005");
        }
        // 这里为了兼容旧数据(旧数据微信注册没有configEventType 当configEventType == null 并且是微信注册事件时返回true）
        if (null == configEventType
            && Objects.equals(PromotionContants.REGISTRY_TYPE_DEFAULT.toString(), registerChannel)) {
            return true;
        }
        // 类型匹配返回true
        if (Objects.nonNull(configEventType) && Objects.equals(registerChannel, configEventType.toString())) {
            return true;
        }
        else {
            logger.info("RegisterBaseEventFlowNodeHandler.judgeRegisterChannel() false! configEventType={},"
                + "registerChannel={}", configEventType, registerChannel);
            return false;
        }
    }

}
