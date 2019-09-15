package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * tc_order_since 自提信信息
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-06-13
 */
@Data
public class OrderSinceDTO extends BaseModel {
    /**
     * tc_order_since 自提信信息-order_no
     */
    private Long orderNo;

    /**
     * tc_order_since 自提信信息-客户自提收到的自提码
     */
    private String sendCode;

    /**
     * tc_order_since 自提信信息-是否已发送短信 1:是 0:否
     */
    private String sendFlag;

    /**
     * tc_order_since 自提信信息-短信发送时间
     */
    private Date sendSmsDate;

    /**
     * tc_order_since 自提信信息-自提日期
     */
    private Date sinceDate;

    /**
     * tc_order_since 自提信信息-订单自提时间
     */
    private Date sinceTime;

    /**
     * tc_order_since 自提信信息-提药时间限制
     */
    private String timeLimit;

    /**
     * tc_order_since 自提信信息-自提售药机（区分哪个售药机）
     */
    private String sinceDrugMachine;
}
