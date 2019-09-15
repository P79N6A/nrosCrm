package com.ztesoft.zsmart.nros.crm.core.client.model.dto;

import com.alibaba.fastjson.JSONObject;
import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class EventTraceTestDTO extends BaseModel implements Serializable {

    /**
     * 事件轨迹表-事件ID
     */
    private Long eventId;

    /**
     * 事件轨迹表-会员ID
     */
    private Long memberId;

    /**
     * 事件轨迹表-语言: zh 中文，en 英文
     */
    private String lang;

    /**
     * 事件内容
     */
    private JSONObject content;

    private static final long serialVersionUID = 1L;

}
