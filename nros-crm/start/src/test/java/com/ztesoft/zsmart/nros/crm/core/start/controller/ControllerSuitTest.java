package com.ztesoft.zsmart.nros.crm.core.start.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author yuanxiaokai
 * @date 2019/7/9
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    BaseMetaDataControllerTest.class, CampaignApiControllerTest.class, CouponControllerTest.class,
    CustomerGroupControllerTest.class, EventTraceControllerTest.class, InviteCampaignControllerTest.class,
    ItemControllerTest.class, MarketingControllerTest.class, MemberControllerTest.class, OrderControllerTest.class,
    PromotionControllerTest.class, SignCampaignControllerTest.class, SmsTemplateControllerTest.class,
    StoreControllerTest.class, UserControllerTest.class, EventTriggerControllerTest.class
})
public class ControllerSuitTest {
}
