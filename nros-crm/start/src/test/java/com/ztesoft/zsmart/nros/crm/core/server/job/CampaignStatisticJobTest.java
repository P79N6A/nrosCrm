package com.ztesoft.zsmart.nros.crm.core.server.job;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhou.xiaofeng
 * @description 活动统计脚本单元测试类
 * @date 2019-06-18
 */
@Slf4j
public class CampaignStatisticJobTest extends MockitoTest {
    /**
     * 注入脚本
     */
    @Autowired
    CampaignStatisticJob campaignStatisticJob;

    /**
     * 定时任务方法测试
     */
    @Test
    public void clickCounterScheduledTest(){
        log.info("inviteCampaignService clickCounterScheduledTest start");
        try {
            System.out.println("test running");
            campaignStatisticJob.clickCounterScheduled();
            log.info("inviteCampaignService clickCounterScheduledTest result ->. ");
        } catch (Exception e) {
            log.error("inviteCampaignService clickCounterScheduledTest fail ->. ", e);
            e.printStackTrace();
        }
        log.info("inviteCampaignService clickCounterScheduledTest end ");


    }

}
