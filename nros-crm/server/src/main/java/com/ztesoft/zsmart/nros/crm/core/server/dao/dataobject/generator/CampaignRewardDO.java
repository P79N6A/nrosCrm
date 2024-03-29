package com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator;

import com.ztesoft.zsmart.nros.common.model.BaseModel;
import java.io.Serializable;

/**
 * 活动奖励
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-06-02
 */
public class CampaignRewardDO extends BaseModel implements Serializable {
    /**
     * 活动奖励-是否选择邀请人优惠券奖励
     */
    private String isCompounInviter;

    /**
     * 活动奖励-邀请人奖励优惠券编码
     */
    private String compounCodeInviter;

    /**
     * 活动奖励-是否选择邀请人积分奖励
     */
    private String isPointInviter;

    /**
     * 活动奖励-邀请人奖励积分
     */
    private Integer pointInviter;

    /**
     * 活动奖励-邀请人奖励积分是否永久有效
     */
    private String pointIsPermanetInviter;

    /**
     * 活动奖励-邀请人积分奖励几日后生效
     */
    private Integer pointvalidAfterDaysInviter;

    /**
     * 活动奖励-是否选择邀请人其他奖励
     */
    private String isOtherInviter;

    /**
     * 活动奖励-邀请人其他奖励
     */
    private String otherInviter;

    /**
     * 活动奖励-是否选择被邀请人优惠券奖励
     */
    private String isCompounInvitee;

    /**
     * 活动奖励-被邀请人奖励优惠券编码
     */
    private String compounCodeInvitee;

    /**
     * 活动奖励-是否选择被邀请人积分奖励
     */
    private String isPointInvitee;

    /**
     * 活动奖励-被邀请人奖励积分
     */
    private Integer pointInvitee;

    /**
     * 活动奖励-被邀请人奖励积分是否永久有效
     */
    private String pointIsPermanetInvitee;

    /**
     * 活动奖励-被邀请人积分奖励几日后生效
     */
    private Integer pointvalidAfterDaysInvitee;

    /**
     * 活动奖励-是否选择被邀请人其他奖励
     */
    private String isOtherInvitee;

    /**
     * 活动奖励-被邀请人其他奖励
     */
    private String otherInvitee;

    /**
     * 活动奖励-活动ID
     */
    private Long campaignId;

    /**
     * This field was generated by MyBatis Generator.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取：活动奖励-是否选择邀请人优惠券奖励
     *
     * @return 是否选择邀请人优惠券奖励
     */
    public String getIsCompounInviter() {
        return isCompounInviter;
    }

    /**
     * 设置：活动奖励-是否选择邀请人优惠券奖励
     *
     * @param isCompounInviter 活动奖励-是否选择邀请人优惠券奖励
     */
    public void setIsCompounInviter(String isCompounInviter) {
        this.isCompounInviter = isCompounInviter == null ? null : isCompounInviter.trim();
    }

    /**
     * 获取：活动奖励-邀请人奖励优惠券编码
     *
     * @return 邀请人奖励优惠券编码
     */
    public String getCompounCodeInviter() {
        return compounCodeInviter;
    }

    /**
     * 设置：活动奖励-邀请人奖励优惠券编码
     *
     * @param compounCodeInviter 活动奖励-邀请人奖励优惠券编码
     */
    public void setCompounCodeInviter(String compounCodeInviter) {
        this.compounCodeInviter = compounCodeInviter == null ? null : compounCodeInviter.trim();
    }

    /**
     * 获取：活动奖励-是否选择邀请人积分奖励
     *
     * @return 是否选择邀请人积分奖励
     */
    public String getIsPointInviter() {
        return isPointInviter;
    }

    /**
     * 设置：活动奖励-是否选择邀请人积分奖励
     *
     * @param isPointInviter 活动奖励-是否选择邀请人积分奖励
     */
    public void setIsPointInviter(String isPointInviter) {
        this.isPointInviter = isPointInviter == null ? null : isPointInviter.trim();
    }

    /**
     * 获取：活动奖励-邀请人奖励积分
     *
     * @return 邀请人奖励积分
     */
    public Integer getPointInviter() {
        return pointInviter;
    }

    /**
     * 设置：活动奖励-邀请人奖励积分
     *
     * @param pointInviter 活动奖励-邀请人奖励积分
     */
    public void setPointInviter(Integer pointInviter) {
        this.pointInviter = pointInviter;
    }

    /**
     * 获取：活动奖励-邀请人奖励积分是否永久有效
     *
     * @return 邀请人奖励积分是否永久有效
     */
    public String getPointIsPermanetInviter() {
        return pointIsPermanetInviter;
    }

    /**
     * 设置：活动奖励-邀请人奖励积分是否永久有效
     *
     * @param pointIsPermanetInviter 活动奖励-邀请人奖励积分是否永久有效
     */
    public void setPointIsPermanetInviter(String pointIsPermanetInviter) {
        this.pointIsPermanetInviter = pointIsPermanetInviter == null ? null : pointIsPermanetInviter.trim();
    }

    /**
     * 获取：活动奖励-邀请人积分奖励几日后生效
     *
     * @return 邀请人积分奖励几日后生效
     */
    public Integer getPointvalidAfterDaysInviter() {
        return pointvalidAfterDaysInviter;
    }

    /**
     * 设置：活动奖励-邀请人积分奖励几日后生效
     *
     * @param pointvalidAfterDaysInviter 活动奖励-邀请人积分奖励几日后生效
     */
    public void setPointvalidAfterDaysInviter(Integer pointvalidAfterDaysInviter) {
        this.pointvalidAfterDaysInviter = pointvalidAfterDaysInviter;
    }

    /**
     * 获取：活动奖励-是否选择邀请人其他奖励
     *
     * @return 是否选择邀请人其他奖励
     */
    public String getIsOtherInviter() {
        return isOtherInviter;
    }

    /**
     * 设置：活动奖励-是否选择邀请人其他奖励
     *
     * @param isOtherInviter 活动奖励-是否选择邀请人其他奖励
     */
    public void setIsOtherInviter(String isOtherInviter) {
        this.isOtherInviter = isOtherInviter == null ? null : isOtherInviter.trim();
    }

    /**
     * 获取：活动奖励-邀请人其他奖励
     *
     * @return 邀请人其他奖励
     */
    public String getOtherInviter() {
        return otherInviter;
    }

    /**
     * 设置：活动奖励-邀请人其他奖励
     *
     * @param otherInviter 活动奖励-邀请人其他奖励
     */
    public void setOtherInviter(String otherInviter) {
        this.otherInviter = otherInviter == null ? null : otherInviter.trim();
    }

    /**
     * 获取：活动奖励-是否选择被邀请人优惠券奖励
     *
     * @return 是否选择被邀请人优惠券奖励
     */
    public String getIsCompounInvitee() {
        return isCompounInvitee;
    }

    /**
     * 设置：活动奖励-是否选择被邀请人优惠券奖励
     *
     * @param isCompounInvitee 活动奖励-是否选择被邀请人优惠券奖励
     */
    public void setIsCompounInvitee(String isCompounInvitee) {
        this.isCompounInvitee = isCompounInvitee == null ? null : isCompounInvitee.trim();
    }

    /**
     * 获取：活动奖励-被邀请人奖励优惠券编码
     *
     * @return 被邀请人奖励优惠券编码
     */
    public String getCompounCodeInvitee() {
        return compounCodeInvitee;
    }

    /**
     * 设置：活动奖励-被邀请人奖励优惠券编码
     *
     * @param compounCodeInvitee 活动奖励-被邀请人奖励优惠券编码
     */
    public void setCompounCodeInvitee(String compounCodeInvitee) {
        this.compounCodeInvitee = compounCodeInvitee == null ? null : compounCodeInvitee.trim();
    }

    /**
     * 获取：活动奖励-是否选择被邀请人积分奖励
     *
     * @return 是否选择被邀请人积分奖励
     */
    public String getIsPointInvitee() {
        return isPointInvitee;
    }

    /**
     * 设置：活动奖励-是否选择被邀请人积分奖励
     *
     * @param isPointInvitee 活动奖励-是否选择被邀请人积分奖励
     */
    public void setIsPointInvitee(String isPointInvitee) {
        this.isPointInvitee = isPointInvitee == null ? null : isPointInvitee.trim();
    }

    /**
     * 获取：活动奖励-被邀请人奖励积分
     *
     * @return 被邀请人奖励积分
     */
    public Integer getPointInvitee() {
        return pointInvitee;
    }

    /**
     * 设置：活动奖励-被邀请人奖励积分
     *
     * @param pointInvitee 活动奖励-被邀请人奖励积分
     */
    public void setPointInvitee(Integer pointInvitee) {
        this.pointInvitee = pointInvitee;
    }

    /**
     * 获取：活动奖励-被邀请人奖励积分是否永久有效
     *
     * @return 被邀请人奖励积分是否永久有效
     */
    public String getPointIsPermanetInvitee() {
        return pointIsPermanetInvitee;
    }

    /**
     * 设置：活动奖励-被邀请人奖励积分是否永久有效
     *
     * @param pointIsPermanetInvitee 活动奖励-被邀请人奖励积分是否永久有效
     */
    public void setPointIsPermanetInvitee(String pointIsPermanetInvitee) {
        this.pointIsPermanetInvitee = pointIsPermanetInvitee == null ? null : pointIsPermanetInvitee.trim();
    }

    /**
     * 获取：活动奖励-被邀请人积分奖励几日后生效
     *
     * @return 被邀请人积分奖励几日后生效
     */
    public Integer getPointvalidAfterDaysInvitee() {
        return pointvalidAfterDaysInvitee;
    }

    /**
     * 设置：活动奖励-被邀请人积分奖励几日后生效
     *
     * @param pointvalidAfterDaysInvitee 活动奖励-被邀请人积分奖励几日后生效
     */
    public void setPointvalidAfterDaysInvitee(Integer pointvalidAfterDaysInvitee) {
        this.pointvalidAfterDaysInvitee = pointvalidAfterDaysInvitee;
    }

    /**
     * 获取：活动奖励-是否选择被邀请人其他奖励
     *
     * @return 是否选择被邀请人其他奖励
     */
    public String getIsOtherInvitee() {
        return isOtherInvitee;
    }

    /**
     * 设置：活动奖励-是否选择被邀请人其他奖励
     *
     * @param isOtherInvitee 活动奖励-是否选择被邀请人其他奖励
     */
    public void setIsOtherInvitee(String isOtherInvitee) {
        this.isOtherInvitee = isOtherInvitee == null ? null : isOtherInvitee.trim();
    }

    /**
     * 获取：活动奖励-被邀请人其他奖励
     *
     * @return 被邀请人其他奖励
     */
    public String getOtherInvitee() {
        return otherInvitee;
    }

    /**
     * 设置：活动奖励-被邀请人其他奖励
     *
     * @param otherInvitee 活动奖励-被邀请人其他奖励
     */
    public void setOtherInvitee(String otherInvitee) {
        this.otherInvitee = otherInvitee == null ? null : otherInvitee.trim();
    }

    /**
     * 获取：活动奖励-活动ID
     *
     * @return 活动ID
     */
    public Long getCampaignId() {
        return campaignId;
    }

    /**
     * 设置：活动奖励-活动ID
     *
     * @param campaignId 活动奖励-活动ID
     */
    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }
}