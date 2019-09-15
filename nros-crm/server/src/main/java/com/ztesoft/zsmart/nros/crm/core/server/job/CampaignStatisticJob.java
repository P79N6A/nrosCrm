package com.ztesoft.zsmart.nros.crm.core.server.job;

import com.ztesoft.zsmart.nros.base.util.ConvertUtil;
import com.ztesoft.zsmart.nros.base.util.paas.CacheUtils;
import com.ztesoft.zsmart.nros.crm.core.client.api.InviteCampaignService;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.CampaignFeedBackParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author zhou.xiaofeng
 * @description 活动统计定时任务
 * @date 2019-06-17
 */
@Component
public class CampaignStatisticJob {

    @Autowired
    private InviteCampaignService inviteCampaignService;

    @Autowired
    private CacheUtils cacheUtils;

    private static final Logger logger = LoggerFactory.getLogger(CampaignStatisticJob.class);

    /**
     * 活动点击记录定时任务
     */
//    @Scheduled(cron = "0/5 0 * * * ?")
    public void clickCounterScheduled() {
        Long start = System.currentTimeMillis();
        logger.info("campaign statistic  job [ClickCounterScheduled] start at: " + start);
        Object feedBackList = cacheUtils.get("nrosCrmCampaignFeedBack");
        //判断是否存在改缓存
        if (feedBackList != null && cacheUtils.exists("nrosCrmCampaignFeedBack")) {
            logger.info("CacheUtils exist nrosCrmCampaignFeedBack cache.......");
            if (feedBackList instanceof ArrayList) {
                ((ArrayList) feedBackList).forEach(item -> {
                    CampaignFeedBackParam feedBackParam = ConvertUtil.beanCopy(item, CampaignFeedBackParam.class);
                    inviteCampaignService.addFeedBackRecord(feedBackParam);
                });
            }
            //删除key对应的value
            cacheUtils.remove("nrosCrmCampaignFeedBack");
        }

        long end = System.currentTimeMillis();
        logger.info("campaign statistic  job [ClickCounterScheduled] end at: "
                + end + ", consumes: " + (end - start) + " ms.");

    }


}
