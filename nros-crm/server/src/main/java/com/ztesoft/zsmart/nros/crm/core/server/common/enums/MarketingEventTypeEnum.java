package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.event.BaseEventNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.event.MemberConsumeEventNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.event.MemberRechargeEventNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.event.RegisterEventNodeHandler;

import java.util.Arrays;

/**
 * 营销事件的类型
 *
 * @author PQ
 * @date 2019/4/17
 */
public enum MarketingEventTypeEnum {

    /**
     * 会员注册
     */
    REGISTER("E0009", "会员注册", RegisterEventNodeHandler.class),

    /**
     * 会员消费
     */
    MEMBERCOSUME("E0012", "会员消费", MemberConsumeEventNodeHandler.class),

    /**
     * 会员充值
     */
    MEMBERRECHARGE("E0013", "会员充值", MemberRechargeEventNodeHandler.class);


    private final String eventCode;

    private final String eventName;

    private Class<? extends BaseEventNodeHandler> className;

    MarketingEventTypeEnum(String eventCode, String eventName, Class<? extends BaseEventNodeHandler> claz) {
        this.eventCode = eventCode;
        this.eventName = eventName;
        this.className = claz;
    }

    public static MarketingEventTypeEnum getByEventCode(String eventCode) throws BusiException {
        return Arrays.stream(MarketingEventTypeEnum.values())
                .filter(eventTypeEnum -> eventTypeEnum.getEventCode().equals(eventCode)).findAny().orElse(null);
    }

    public String getEventCode() {
        return eventCode;
    }

    public String getEventName() {
        return eventName;
    }

    public Class<? extends BaseEventNodeHandler> getClassName() {
        return className;
    }

}
