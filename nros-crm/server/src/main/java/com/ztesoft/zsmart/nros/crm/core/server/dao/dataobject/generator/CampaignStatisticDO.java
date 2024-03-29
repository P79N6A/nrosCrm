package com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import java.io.Serializable;

/**
 * 营销活动统计
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-06-12
 */
public class CampaignStatisticDO extends BaseModel implements Serializable {
    /**
     * 营销活动统计-点击量
     */
    private Integer clickCounter;

    /**
     * 营销活动统计-访问量
     */
    private Integer pageviewCounter;

    /**
     * 营销活动统计-停留时间
     */
    private String stayTime;

    /**
     * 营销活动统计-留存时间
     */
    private Integer stayTimeCounter;

    /**
     * 营销活动统计-新注册人数
     */
    private Integer registerNum;

    /**
     * 营销活动统计-参与推荐人数
     */
    private Integer recommandNum;

    /**
     * 营销活动统计-微信分享总次数
     */
    private Integer wxShareCounter;

    /**
     * 营销活动统计-实际参加人数
     */
    private Integer realAttendNum;

    /**
     * 营销活动统计-活动类型
     */
    private String campaignType;

    /**
     * 营销活动统计-活动ID
     */
    private Long campaignId;

    /**
     * 营销活动统计-报名人数
     */
    private Integer signUpNum;

    /**
     * 营销活动统计-签到人数
     */
    private Integer signInNum;

    /**
     * This field was generated by MyBatis Generator.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取：营销活动统计-点击量
     *
     * @return 点击量
     */
    public Integer getClickCounter() {
        return clickCounter;
    }

    /**
     * 设置：营销活动统计-点击量
     *
     * @param clickCounter 营销活动统计-点击量
     */
    public void setClickCounter(Integer clickCounter) {
        this.clickCounter = clickCounter;
    }

    /**
     * 获取：营销活动统计-访问量
     *
     * @return 访问量
     */
    public Integer getPageviewCounter() {
        return pageviewCounter;
    }

    /**
     * 设置：营销活动统计-访问量
     *
     * @param pageviewCounter 营销活动统计-访问量
     */
    public void setPageviewCounter(Integer pageviewCounter) {
        this.pageviewCounter = pageviewCounter;
    }

    /**
     * 获取：营销活动统计-停留时间
     *
     * @return 停留时间
     */
    public String getStayTime() {
        return stayTime;
    }

    /**
     * 设置：营销活动统计-停留时间
     *
     * @param stayTime 营销活动统计-停留时间
     */
    public void setStayTime(String stayTime) {
        this.stayTime = stayTime == null ? null : stayTime.trim();
    }

    /**
     * 获取：营销活动统计-留存时间
     *
     * @return 留存时间
     */
    public Integer getStayTimeCounter() {
        return stayTimeCounter;
    }

    /**
     * 设置：营销活动统计-留存时间
     *
     * @param stayTimeCounter 营销活动统计-留存时间
     */
    public void setStayTimeCounter(Integer stayTimeCounter) {
        this.stayTimeCounter = stayTimeCounter;
    }

    /**
     * 获取：营销活动统计-新注册人数
     *
     * @return 新注册人数
     */
    public Integer getRegisterNum() {
        return registerNum;
    }

    /**
     * 设置：营销活动统计-新注册人数
     *
     * @param registerNum 营销活动统计-新注册人数
     */
    public void setRegisterNum(Integer registerNum) {
        this.registerNum = registerNum;
    }

    /**
     * 获取：营销活动统计-参与推荐人数
     *
     * @return 参与推荐人数
     */
    public Integer getRecommandNum() {
        return recommandNum;
    }

    /**
     * 设置：营销活动统计-参与推荐人数
     *
     * @param recommandNum 营销活动统计-参与推荐人数
     */
    public void setRecommandNum(Integer recommandNum) {
        this.recommandNum = recommandNum;
    }

    /**
     * 获取：营销活动统计-微信分享总次数
     *
     * @return 微信分享总次数
     */
    public Integer getWxShareCounter() {
        return wxShareCounter;
    }

    /**
     * 设置：营销活动统计-微信分享总次数
     *
     * @param wxShareCounter 营销活动统计-微信分享总次数
     */
    public void setWxShareCounter(Integer wxShareCounter) {
        this.wxShareCounter = wxShareCounter;
    }

    /**
     * 获取：营销活动统计-实际参加人数
     *
     * @return 实际参加人数
     */
    public Integer getRealAttendNum() {
        return realAttendNum;
    }

    /**
     * 设置：营销活动统计-实际参加人数
     *
     * @param realAttendNum 营销活动统计-实际参加人数
     */
    public void setRealAttendNum(Integer realAttendNum) {
        this.realAttendNum = realAttendNum;
    }

    /**
     * 获取：营销活动统计-活动类型
     *
     * @return 活动类型
     */
    public String getCampaignType() {
        return campaignType;
    }

    /**
     * 设置：营销活动统计-活动类型
     *
     * @param campaignType 营销活动统计-活动类型
     */
    public void setCampaignType(String campaignType) {
        this.campaignType = campaignType == null ? null : campaignType.trim();
    }

    /**
     * 获取：营销活动统计-活动ID
     *
     * @return 活动ID
     */
    public Long getCampaignId() {
        return campaignId;
    }

    /**
     * 设置：营销活动统计-活动ID
     *
     * @param campaignId 营销活动统计-活动ID
     */
    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * 获取：营销活动统计-报名人数
     *
     * @return 报名人数
     */
    public Integer getSignUpNum() {
        return signUpNum;
    }

    /**
     * 设置：营销活动统计-报名人数
     *
     * @param signUpNum 营销活动统计-报名人数
     */
    public void setSignUpNum(Integer signUpNum) {
        this.signUpNum = signUpNum;
    }

    /**
     * 获取：营销活动统计-签到人数
     *
     * @return 签到人数
     */
    public Integer getSignInNum() {
        return signInNum;
    }

    /**
     * 设置：营销活动统计-签到人数
     *
     * @param signInNum 营销活动统计-签到人数
     */
    public void setSignInNum(Integer signInNum) {
        this.signInNum = signInNum;
    }
}