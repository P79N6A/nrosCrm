package com.ztesoft.zsmart.nros.crm.core.client.model.query;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ztesoft.zsmart.nros.common.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询活动报名列表参数
 *
 * @author fan.chaolin
 * @date 2019/4/13
 */
@Data
@ApiModel
public class SignCampaignQuery extends BaseQuery implements Serializable {

    /**
     * 活动-活动id
     */
    private Long id;

    /**
     * 活动-商品Id
     */
    private String merchantId;

    /**
     * 活动-活动名称
     */
    private String name;

    /**
     * 活动-创建人
     */
    private JSONObject creator;

    /**
     * 活动-创建时间区间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date gmtCreateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date gmtCreateEnd;

    /**
     * 活动-活动开始时间区间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTimeFore;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTimeAfter;


    /**
     * 活动-活动结束时间区间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTimeFore;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTimeAfter;

    /**
     * 活动-活动状态
     */
    private String campaignState;

    /**
     * 活动-状态
     */
    //private String status;

}
