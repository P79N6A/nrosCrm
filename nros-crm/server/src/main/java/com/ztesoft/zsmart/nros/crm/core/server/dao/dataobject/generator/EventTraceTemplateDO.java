package com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import java.io.Serializable;

/**
 * 定义事件轨迹内容的模板
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-06-02
 */
public class EventTraceTemplateDO extends BaseModel implements Serializable {
    /**
     * 定义事件轨迹内容的模板-event_id
     */
    private Long eventId;

    /**
     * 定义事件轨迹内容的模板-语言: zh 中文，en 英文
     */
    private String lang;

    /**
     * 定义事件轨迹内容的模板-模板的内容，变量使用${}占位，比如：订单号：${order_no}
     */
    private String templateContent;

    /**
     * This field was generated by MyBatis Generator.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取：定义事件轨迹内容的模板-event_id
     *
     * @return event_id
     */
    public Long getEventId() {
        return eventId;
    }

    /**
     * 设置：定义事件轨迹内容的模板-event_id
     *
     * @param eventId
     */
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    /**
     * 获取：定义事件轨迹内容的模板-语言: zh 中文，en 英文
     *
     * @return 语言: zh 中文，en 英文
     */
    public String getLang() {
        return lang;
    }

    /**
     * 设置：定义事件轨迹内容的模板-语言: zh 中文，en 英文
     *
     * @param lang 定义事件轨迹内容的模板-语言: zh 中文，en 英文
     */
    public void setLang(String lang) {
        this.lang = lang == null ? null : lang.trim();
    }

    /**
     * 获取：定义事件轨迹内容的模板-模板的内容，变量使用${}占位，比如：订单号：${order_no}
     *
     * @return 模板的内容，变量使用${}占位，比如：订单号：${order_no}
     */
    public String getTemplateContent() {
        return templateContent;
    }

    /**
     * 设置：定义事件轨迹内容的模板-模板的内容，变量使用${}占位，比如：订单号：${order_no}
     *
     * @param templateContent 定义事件轨迹内容的模板-模板的内容，变量使用${}占位，比如：订单号：${order_no}
     */
    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent == null ? null : templateContent.trim();
    }
}