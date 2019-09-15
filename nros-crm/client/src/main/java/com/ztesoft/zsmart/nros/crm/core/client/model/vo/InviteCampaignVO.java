package com.ztesoft.zsmart.nros.crm.core.client.model.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动VO
 * @date 2019-04-13
 */
@Data
public class InviteCampaignVO implements Serializable {
    /**
     * 活动-标识
     */
    private Long id;

    /**
     * 活动-活动名称
     */
    private String name;

    /**
     * 活动-创建时间
     */
    private Date gmtCreate;

    /**
     * 活动-创建人
     */
    private Long creator;

    /**
     * 活动-创建人名称
     */
    private String creatorName;

    /**
     * 活动-活动状态:[0]设计中,[1]审核中,[2]审核不通过,[3]未开始,[4]进行中,[5]暂停中,[6]已结束
     */
    private String campaignState;

    /**
     * 活动-活动状态名称
     */
    private String campaignStateName;

    /**
     * 活动-活动链接地址
     */
    private String linkAddress;
}
