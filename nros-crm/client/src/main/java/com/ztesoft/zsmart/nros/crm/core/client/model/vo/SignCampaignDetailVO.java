package com.ztesoft.zsmart.nros.crm.core.client.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author yangkai
 * @description 报名活动详情VO
 * @date 2019-06-10
 */
@Data
public class SignCampaignDetailVO implements Serializable {
    /**
     * 活动-标识
     */
    private Long id;

    /**
     * 活动-活动名称
     */
    private String name;

    /**
     * 活动-活动地点
     */
    private String location;

    /**
     * 活动-活动详情
     */
    private String richDetail;

    /**
     * 活动状态
     */
    private String campaignState;

    /**
     * 活动-活动开始时间
     */
    private Date startTime;

    /**
     * 活动-活动开始时间
     */
    private Date endTime;

    /**
     * 活动-活动开始时间
     */
    private Date signStartTime;

    /**
     * 活动-活动开始时间
     */
    private Date signEndTime;

    /**
     * 活动-活动链接地址
     */
    private String linkAddress;

    /**
     * 活动-创建时间
     */
    private Date gmtCreate;

    /**
     * 活动-微信转发图(二维码)
     */
    private String wxPic;

    /**
     * 活动统计-点击量--今日访问量
     */
    private Integer clickCounter;

    /**
     * 活动统计-访问量
     */
    private Integer pageviewCounter;

    /**
     * 活动统计-报名人数
     */
    private Integer signUpNum;

    /**
     * 活动统计-签到人数
     */
    private Integer signInNum;


}
