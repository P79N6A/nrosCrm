package com.ztesoft.zsmart.nros.crm.core.client.model.vo;



import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动详情信息VO
 * @date 2019-06-05
 */
@Data
public class InviteCampaignDetailVO implements Serializable {

    private static final long serialVersionUID = 8194491536286499538L;
    /**
     * 活动-标识
     */
    private Long id;

    /**
     * 活动-活动名称
     */
    private String name;

    /**
     * 活动状态
     */
    private String status;

    /**
     * 活动-活动状态:[0]设计中,[1]审核中,[2]审核不通过,[3]未开始,[4]进行中,[5]暂停中,[6]已结束
     */
    private String campaignState;

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
     * 活动统计-点击量
     */
    private Integer clickCounter;

    /**
     * 活动统计-访问量
     */
    private Integer pageviewCounter;

    /**
     * 活动统计-今日访问量
     */
    private Integer todayCounter;

    /**
     * 活动统计-新注册人数
     */
    private Integer registerNum;

    /**
     * 活动统计-参与推荐人数
     */
    private Integer recommandNum;

    /**
     * 邀请活动-邀请明细列表
     */
    List<InviteDetailVO> inviteDetailList;

}
