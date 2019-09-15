package com.ztesoft.zsmart.nros.crm.core.client.model.query;

import com.ztesoft.zsmart.nros.common.model.BaseQuery;
import lombok.Data;

/**
 * 查询报名用户列表参数
 * @author yangkai
 * @date 2019-06-15
 */
@Data
public class SignInListQuery extends BaseQuery {

    /**
     * 活动id
     */
    private Long campaignId;

    /**
     * 报名时间开始
     */
    private String signTimeFore;

    /**
     * 报名时间结束
     */
    private String signTimeAfter;

    /**
     * 签到时间开始
     */
    private String signInTimeFore;

    /**
     * 签到时间结束
     */
    private String signInTimeAfter;

    /**
     * 是否签到
     */
    private String isSignIn;
}
