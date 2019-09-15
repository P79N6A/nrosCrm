package com.ztesoft.zsmart.nros.crm.core.client.model.dto;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 事件营销分析DTO
 *
 * @author fan.chaolin
 * @date 2019/4/19
 */
@Data
public class MarketingEventStatisticsDTO extends BaseModel implements Serializable {
    /**
     * 今日触发次数
     */
    private Integer triggerCountToday;

    /**
     * 触发总次数
     */
    private Integer triggerCountAll;

    /**
     * 流程完成人数
     */
    private Integer processFinishedCount;

    /**
     * 完成率
     */
    private String finishedPercent;

}
