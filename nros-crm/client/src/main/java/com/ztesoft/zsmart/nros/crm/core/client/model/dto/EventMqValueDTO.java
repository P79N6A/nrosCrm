package com.ztesoft.zsmart.nros.crm.core.client.model.dto;

import lombok.Data;

/**
 * 事件轨迹mq主体信息
 *
 */
@Data
public class EventMqValueDTO {

    private String fieldCode;

    private String fieldValue;
}
