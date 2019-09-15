package com.ztesoft.zsmart.nros.crm.core.server.common.enums;

import java.util.Arrays;

import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action.SendCouponActionHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action.SendPointActionHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action.SendSmsActionHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action.TaggedActionHandler;

/**
 * @author admin
 * @date 2018/3/15
 **/
public enum MarketingActionTypeEnum {

    /**
     * 发短信
     */
    SENDSMS("0", "发短信", SendSmsActionHandler.class),

    /**
     * 送积分
     */
    SENDPOINT("3", "送积分", SendPointActionHandler.class),

    /**
     * 赠送优惠券
     */
    SENDCOUPON("4", "赠送优惠券", SendCouponActionHandler.class),

    /**
     * 打标签
     */
    ADDTAG("6", "打标签", TaggedActionHandler.class);

    private String value;

    private String code;

    private Class clazz;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public Class getClazz() {
        return clazz;
    }

    MarketingActionTypeEnum(String code, String value, Class clazz) {
        this.code = code;
        this.value = value;
        this.clazz = clazz;
    }

    public static MarketingActionTypeEnum getByCode(String code) throws BusiException {
        return Arrays.stream(MarketingActionTypeEnum.values()).filter(typeEnum -> typeEnum.getCode().equals(code))
            .findAny().orElse(null);
    }
}
