/**
 * [Product]
 * nrospromotion
 * [Copyright]
 * Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 * SmsUtil.java
 * [History]
 * Version  Date      Author     Content
 * -------- --------- ---------- ------------------------
 * 1.0.0    2019/6/21   PQ         最初版本
 */
package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.common.collect.Lists;
import com.ztesoft.zsmart.nros.base.util.StringUtil;
import com.ztesoft.zsmart.nros.crm.core.server.common.constant.PromotionContants;

import lombok.Data;

/**
 * 发送短信工具类
 * 
 * @author PQ
 * @date 2019/6/21
 */
@Component
@Data
public class SmsUtil {
    private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.accessKeySecret}")
    private String accessSecret;

    @Value("${aliyun.smsSignName}")
    private String smsSignName;

    @Value("${aliyun.smsProduct}")
    private String smsProduct;

    @Value("${aliyun.smsDoamin}")
    private String smsDoamin;

    @Value("${aliyun.defaultTemplateCode}")
    private String defaultTemplateCode;

    /**
     * 批量发送内容统一的短信
     *
     * @param content 短信内容
     * @param phoneList 手机列表
     * @return void
     * @author PQ
     * @date 2019/6/21
     */
    @Async
    public void batchSendSms(String content, List<String> phoneList) {
        if (CollectionUtils.isEmpty(phoneList) || StringUtil.isNull(content)) {
            logger.info("SmsUtil.batchSendSms()……params is null!");
            return;
        }
        DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        // 拼接请求参数
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setAction("SendSms");
        request.setVersion("2017-05-25");
        request.putQueryParameter("SignName", smsSignName);
        request.putQueryParameter("TemplateCode", defaultTemplateCode);
        request.setDomain(smsDoamin);
        JSONObject smsJson = new JSONObject();
        smsJson.put("content", content);
        request.putQueryParameter("TemplateParam", smsJson.toJSONString());
        // 对phoneList分区执行（一次发送的批量上限为1000个手机号码）
        com.google.common.collect.Lists.partition(phoneList, PromotionContants.ALI_SEND_SMS_LIMIT_SIZE)
            .forEach(partitionPhoneList -> {
                try {
                    request.putQueryParameter("PhoneNumbers", StringUtils.join(partitionPhoneList, ","));
                    logger.info("SmsUtil.batchSendSms()……begin to send SMS:content={}", content);
                    CommonResponse response = client.getCommonResponse(request);
                    logger.info("SmsUtil.batchSendSms()……result:{}", response.getData());
                }
                catch (Exception e) {
                    logger.error("SmsUtil.batchSendSms() error! msg:[{}]!", e.getMessage());
                }
            });
    }

    /**
     * 批量发送个性化动态内容
     * 
     * @param contentList 个性化内容列表
     * @param phoneList 手机列表
     * @return void
     * @author PQ
     * @date 2019/6/21
     */
    @Async
    public void batchSendDynamicsSms(List<String> contentList, List<String> phoneList) {
        if (CollectionUtils.isEmpty(contentList) || CollectionUtils.isEmpty(phoneList)
            || contentList.size() != phoneList.size()) {
            logger.info("SmsUtil.batchSendDynamicsSms()……params is null or paramList size not matching!");
            return;
        }
        try {
            DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessSecret);
            DefaultProfile.addEndpoint("default", "default", smsProduct, smsDoamin);
            // DefaultProfile.addEndpoint("default", smsProduct, "cn-beijing");
            IAcsClient acsClient = new DefaultAcsClient(profile);
            // 组装请求对象
            SendBatchSmsRequest request = new SendBatchSmsRequest();
            // 使用post提交
            request.setMethod(MethodType.POST);
            // 必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(defaultTemplateCode);

            // 针对阿里云短信网关批量发送手机号最大为100的限制，分区执行
            List<List<String>> partitionPhoneList = Lists.partition(phoneList,
                PromotionContants.ALI_SEND_BATCH_SMS_LIMIT_SIZE);
            List<List<String>> partitionContentListt = Lists.partition(contentList,
                PromotionContants.ALI_SEND_BATCH_SMS_LIMIT_SIZE);
            int partitionSize = partitionPhoneList.size();
            for (int i = 0; i < partitionSize; i++) {
                List<String> innerPartitionPhoneList = partitionPhoneList.get(i);
                List<String> innerPartitionContentList = partitionContentListt.get(i);
                int batchSize = innerPartitionPhoneList.size();
                // 必填:短信签名-支持不同的号码发送不同的短信签名
                JSONArray signNameJson = new JSONArray(batchSize);
                // 必填:待发送手机号。支持JSON格式的批量调用，批量上限为100个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
                JSONArray phoneNumberJson = new JSONArray(batchSize);
                // 必填:模板中的变量替换JSON串
                JSONArray templateParamJson = new JSONArray(batchSize);
                for (int j = 0; j < batchSize; j++) {
                    // 拼接签名
                    signNameJson.set(j, smsSignName);
                    // 拼接手机号
                    phoneNumberJson.set(j, innerPartitionPhoneList.get(j));
                    // 拼接模板内容参数
                    JSONObject templateParamObj = new JSONObject();
                    templateParamObj.put("content", innerPartitionContentList.get(j));
                    templateParamJson.set(j, templateParamObj);
                }
                request.setSignNameJson(signNameJson.toJSONString());
                request.setPhoneNumberJson(phoneNumberJson.toJSONString());
                request.setTemplateParamJson(templateParamJson.toJSONString());
                logger.info("SmsUtil.batchSendDynamicsSms()……begin to send SMS……phone count:{}", batchSize);
                // 请求失败这里会抛ClientException异常
                SendBatchSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
                logger.info("SmsUtil.batchSendDynamicsSms()……result:{}", sendSmsResponse.getMessage());
            }

        }
        catch (Exception e) {
            logger.error("SmsUtil.batchSendDynamicsSms() error! msg:[{}]!", e.getMessage());
        }
    }
}
