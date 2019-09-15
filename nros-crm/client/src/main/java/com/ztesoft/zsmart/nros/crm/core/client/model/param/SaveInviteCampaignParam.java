package com.ztesoft.zsmart.nros.crm.core.client.model.param;

import com.alibaba.fastjson.JSONArray;
import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhou.xiaofeng
 * @description 邀请活动保存参数
 * @date 2019-04-10
 */

@Data
public class SaveInviteCampaignParam extends BaseModel implements Serializable {

    @ApiModelProperty(value = "活动名称", dataType = "String", example = "邀请新用户送券", required = true, position = 4)
    private String name;


    @ApiModelProperty(value = "活动类型", dataType = "String", example = "邀请有礼", required = true, position = 5)
    private String campaignType;

    /**
     * 活动-报名开始时间
     */
    @ApiModelProperty(value = "报名开始时间")
    private Date signStartTime;

    /**
     * 活动-报名结束时间
     */
    @ApiModelProperty(value = "报名结束时间", dataType = "Date")

    private Date signEndTime;

    /**
     * 活动-背景图片
     */
    @ApiModelProperty(value = "背景图片")

    private String backImg;

    /**
     * 活动-活动报名按钮颜色
     */
    @ApiModelProperty(value = "活动报名按钮颜色")
    private String buttonColor;

    /**
     * 活动-活动报名按钮文案
     */
    @ApiModelProperty(value = "活动报名按钮文案")
    private String buttonText;

    /**
     * 活动-联系人手机号
     */
    @ApiModelProperty(value = "联系人手机号")
    private String contactPhone;

    /**
     * 活动-活动开始时间
     */
    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;

    /**
     * 活动-活动结束时间
     */
    @ApiModelProperty(value = "报名结束时间")
    private Date endTime;

    /**
     * 活动-活动报名活动地点
     */
    @ApiModelProperty(value = "活动报名活动地点")
    private String location;

    /**
     * 活动-活动链接地址
     */
    @ApiModelProperty(value = "活动链接地址")
    private String linkAddress;

    /**
     * 活动-活动报名首图
     */
    @ApiModelProperty(value = "活动报名首图")
    private String richPic;

    /**
     * 活动-报名必填信息
     */
    @ApiModelProperty(value = "报名必填信息")
    private String necessoryInfo;

    /**
     * 活动-活动摘要
     */
    @ApiModelProperty(value = "活动摘要")
    private String richDigest;

    /**
     * 活动-活动详情--活动规则
     */
    @ApiModelProperty(value = "活动详情", dataType = "String", example = "满100减20", required = true)

    private String richDetail;

    /**
     * 活动-H5访问地址
     */
    @ApiModelProperty(value = "H5访问地址")

    private String viewAddress;

    /**
     * 活动-微信转发描述
     */
    @ApiModelProperty(value = "微信转发描述")
    private String wxDescription;

    /**
     * 活动-微信转发图
     */
    @ApiModelProperty(value = "微信转发图")

    private String wxPic;

    /**
     * 活动-微信转发标题
     */
    @ApiModelProperty(value = "微信转发标题")

    private String wxTitle;

    /**
     * 活动-可预约开始时间
     */
    @ApiModelProperty(value = "可预约开始时间")

    private Date reserveStartTime;

    /**
     * 活动-可预约结束时间
     */
    @ApiModelProperty(value = "可预约结束时间")
    private Date reserveEndTime;

    /**
     * 活动-活动人数
     */
    @ApiModelProperty(value = "活动人数")
    private Integer attendNumControl;

    /**
     * 活动-是否审核
     */
    @ApiModelProperty(value = "是否审核")
    private String isAuditOpen;

    /**
     * 活动-提交成功页面提示标题
     */
    @ApiModelProperty(value = "提交成功页面提示标题")
    private String submitTitle;

    /**
     * 活动-提交成功页面提示描述
     */
    @ApiModelProperty(value = "提交成功页面提示描述")
    private String submitDescription;

    /**
     * 活动-是否开启签到码
     */
    @ApiModelProperty(value = "是否开启签到码")
    private String isSignInOpen;

    /**
     * 活动-活动状态:[0]设计中,[1]审核中,[2]审核不通过,[3]未开始,[4]进行中,[5]暂停中,[6]已结束
     */
    @ApiModelProperty(value = "活动状态", dataType = "String", example = "[0]设计中,[1]审核中,[2]审核不通过,[3]未开始,[4]进行中,[5]暂停中,[6]已结束")
    private String campaignState;

    /**
     * 活动-淘宝h5地址
     */
    @ApiModelProperty(value = "淘宝h5地址")
    private String tbUrl;

    /**
     * 活动-微信h5地址
     */
    @ApiModelProperty(value = "微信h5地址")
    private String wxUrl;

    /**
     * 活动-是否长期活动
     */
    @ApiModelProperty(value = "是否长期活动")
    private String isLongTermActivity;

    /**
     * 活动-是否长期可预约
     */
    @ApiModelProperty(value = "是否长期可预约")
    private String isLongTermReservation;

    /**
     * 活动-可预约星期日
     */
    @ApiModelProperty(value = "可预约星期日")
    private String reservationSelectableWeekdays;

    /**
     * 活动-可预约门店
     */
    @ApiModelProperty(value = "可预约门店")
    private JSONArray reservationSelectableStores;

    /**
     * 活动-可提前多少天进行预约
     */
    @ApiModelProperty(value = "可提前多少天进行预约")
    private Integer daysAheadReservation;

    /**
     * 活动-公众号授权appId
     */
    @ApiModelProperty(value = "公众号授权appId")
    private String appid;

    /**
     * 活动奖励-是否选择邀请人优惠券奖励
     */
    @ApiModelProperty(value = "是否选择邀请人优惠券奖励")
    private String isCompounInviter;

    /**
     * 活动奖励-邀请人奖励优惠券编码
     */
    @ApiModelProperty(value = "邀请人奖励优惠券编码")
    private String compounCodeInviter;

    /**
     * 活动奖励-是否选择邀请人积分奖励
     */
    @ApiModelProperty(value = "是否选择邀请人积分奖励")
    private String isPointInviter;

    /**
     * 活动奖励-邀请人奖励积分
     */
    @ApiModelProperty(value = "邀请人奖励积分")
    private Integer pointInviter;

    /**
     * 活动奖励-邀请人奖励积分是否永久有效
     */
    @ApiModelProperty(value = "邀请人奖励积分是否永久有效")
    private String pointIsPermanetInviter;

    /**
     * 活动奖励-邀请人积分奖励几日后生效
     */
    @ApiModelProperty(value = "邀请人积分奖励几日后生效")
    private Integer pointvalidAfterDaysInviter;


    /**
     * 活动奖励-邀请人其他奖励
     */
    @ApiModelProperty(value = "邀请人其他奖励")
    private String otherInviter;

    /**
     * 活动奖励-是否选择被邀请人优惠券奖励
     */
    @ApiModelProperty(value = "是否选择被邀请人优惠券奖励")
    private String isCompounInvitee;

    /**
     * 活动奖励-被邀请人奖励优惠券编码
     */
    @ApiModelProperty(value = "被邀请人奖励优惠券编码")
    private String compounCodeInvitee;

    /**
     * 活动奖励-是否选择被邀请人积分奖励
     */
    @ApiModelProperty(value = "是否选择被邀请人积分奖励")
    private String isPointInvitee;

    /**
     * 活动奖励-被邀请人奖励积分
     */
    @ApiModelProperty(value = "被邀请人奖励积分")
    private Integer pointInvitee;

    /**
     * 活动奖励-被邀请人奖励积分是否永久有效
     */
    @ApiModelProperty(value = "被邀请人奖励积分是否永久有效")
    private String pointIsPermanetInvitee;

    /**
     * 活动奖励-被邀请人积分奖励几日后生效
     */
    @ApiModelProperty(value = "被邀请人积分奖励几日后生效")
    private Integer pointvalidAfterDaysInvitee;

    /**
     * 活动奖励-是否选择被邀请人其他奖励
     */
    @ApiModelProperty(value = "是否选择被邀请人其他奖励")
    private String isOtherInvitee;

    /**
     * 活动奖励-被邀请人其他奖励
     */
    @ApiModelProperty(value = "被邀请人其他奖励")
    private String otherInvitee;
}