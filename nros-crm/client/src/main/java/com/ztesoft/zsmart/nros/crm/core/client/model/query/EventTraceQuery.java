package com.ztesoft.zsmart.nros.crm.core.client.model.query;

import lombok.Data;

/**
 * 获取动态轨迹列表请求参数
 *
 * @author litao
 * @date 2019/6/12
 */
@Data
public class EventTraceQuery {

    /**
     * 事件类型
     */
    private String eventCode;

    /**
     * 事件轨迹表-语言: zh 中文，en 英文
     */
    private String lang;

    /**
     * 事件轨迹表-会员ID
     */
    private Long memberId;

    /**
     * 页数
     */
    private int pageIndex;

    /**
     * 每页数据数
     */
    private int pageSize;
}
