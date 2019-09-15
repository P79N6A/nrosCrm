package com.ztesoft.zsmart.nros.crm.core.client.model.dto;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 会员轨迹列表DTO
 *
 * @author litao
 * @date 2019/6/12
 */
@Data
public class EventTraceListDTO extends BaseModel implements Serializable {

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
    private String content;

    private static final long serialVersionUID = 1L;

}
