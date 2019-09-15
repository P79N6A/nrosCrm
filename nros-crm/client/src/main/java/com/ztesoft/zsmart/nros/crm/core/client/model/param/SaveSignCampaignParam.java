package com.ztesoft.zsmart.nros.crm.core.client.model.param;

import com.alibaba.fastjson.JSONArray;
import com.ztesoft.zsmart.nros.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 报名活动保存参数
 * @author fan.chaolin
 * @date 2019/4/12
 */
@Data
@ApiModel("保存报名活动参数")
public class SaveSignCampaignParam extends BaseModel implements Serializable {

    @ApiModelProperty(value = "活动Id")
    private Long id;

    @ApiModelProperty(value = "活动名称", example = "报名活动", required = true)
    private String name;

    @ApiModelProperty(value = "活动类型", example = "1: 报名活动， 2: 邀请活动")
    private String campaignType;

    @ApiModelProperty(value = "报名开始时间")
    private Date signStartTime;

    @ApiModelProperty(value = "报名结束时间")
    private Date signEndTime;

    @ApiModelProperty(value = "活动背景图片")
    private String backImg;

    @ApiModelProperty(value = "报名活钮文案", required = true)
    private String buttonText;

    @ApiModelProperty(value = "活动联系人手机号")
    private String contactPhone;

    @ApiModelProperty(value = "活动开始时间", required = true)
    private Date startTime;

    @ApiModelProperty(value = "活动结束时间", required = true)
    private Date endTime;

    @ApiModelProperty(value = "报名活动地点", required = true)
    private String location;

    @ApiModelProperty(value = "活动链接地址")
    private String linkAddress;

    @ApiModelProperty(value = "报名活动首图")
    private String richPic;

    @ApiModelProperty(value = "报名必填信息")
    private String necessoryInfo;

    @ApiModelProperty(value = "报名活动摘要")
    private String richDigest;

    @ApiModelProperty(value = "活动详情")
    private String richDetail;

    @ApiModelProperty(value = "H5访问地址")
    private String viewAddress;

    @ApiModelProperty(value = "微信转发描述")
    private String wxDescription;

    @ApiModelProperty(value = "微信转发图片")
    private String wxPic;

    @ApiModelProperty(value = "微信转发标题")
    private String wxTitle;

    @ApiModelProperty(value = "活动人数")
    private Integer attendNumControl;

    @ApiModelProperty(value = "活动页面提交标题")
    private String submitTitle;

    @ApiModelProperty(value = "提交页面描述")
    private String submitDescription;

    @ApiModelProperty(value = "是否开启签到码")
    private String isSignInOpen;

    @ApiModelProperty(value = "是否开启审核")
    private String isAuditOpen;

    /**
     * 活动-活动状态:[0]设计中,[1]审核中,[2]审核不通过,[3]未开始,[4]进行中,[5]暂停中,[6]已结束
     */
    @ApiModelProperty(value = "活动状态", example = "[0]设计中,[1]审核中,[2]审核不通过,[3]未开始,[4]进行中,[5]暂停中,[6]已结束")
    private String campaignState;

    @ApiModelProperty(value = "淘宝h5地址")
    private String tbUrl;

    @ApiModelProperty(value = "微信h5地址")
    private String wxUrl;

    @ApiModelProperty(value = "是否长期活动")
    private String isLongTermActivity;

    @ApiModelProperty(value = "是否长期可预约")
    private String isLongTermReservation;

    @ApiModelProperty(value = "可预约星期日")
    private String reservationSelectableWeekdays;

    @ApiModelProperty(value = "可预约门店")
    private JSONArray reservationSelectableStores;

    @ApiModelProperty(value = "可提前多少天预约")
    private Integer daysAheadReservation;

    @ApiModelProperty(value = "可预约时间段")
    private List<CampaignReserveTimeParam> campaignReserveTimeParamList;

    private static final long serialVersionUID = 1L;
}
