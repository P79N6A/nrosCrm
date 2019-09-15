package com.ztesoft.zsmart.nros.crm.core.client.model.param;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 事件轨迹传入参数
 *
 * @author litao
 * @date 2019/6/12
 */
@Data
public class EventTraceParam extends BaseModel implements Serializable {

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
     * 事件轨迹表-内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}
