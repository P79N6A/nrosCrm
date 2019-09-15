package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Lists;
import com.ztesoft.zsmart.nros.base.util.ReflectUtil;
import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.base.util.StringUtil;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums.MarketingNodeExecuteTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.BaseFlowNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils.SmsUtil;

/**
 * 执行发短信动作
 * 
 * @return
 * @author PQ
 * @date 2019/6/20
 */
@Component
public class SendSmsActionHandler extends BaseFlowNodeHandler {
    // public class SendSmsActionHandler implements JavaDelegate {

    private static final Pattern PATTERN = Pattern.compile("(\\$\\{[a-zA-Z]+\\})");

    private static Logger logger = LoggerFactory.getLogger(SendSmsActionHandler.class);

    public void handler(String json, List<TargetUserDTO> targetUserListYes) {
        try {
            if (CollectionUtils.isEmpty(targetUserListYes)) {
                logger.info("SendSmsActionHandler.handler()……targetUserListYes is empty!!");
                return;
            }
            // logger.info("SendSmsActionHandler.nodeJsonParam=:{}", json);
            JSONObject actionParamObj = JSON.parseObject(json);
            // 营销流程中定义的短信内容
            String smsContentDefine = actionParamObj.getString("smsContent");
            if (StringUtil.isNull(smsContentDefine)) {
                logger.info("SendSmsActionHandler.handler()……smsContent is empty!!");
                return;
            }

            // 短信定义中需要替换的占位符参数列表
            List<String> smsContentParamList = Lists.newArrayList();
            // 正则匹配，判断短信内容中是否存在需要替换的占位符，形如：${英文字母}
            Matcher matcher = PATTERN.matcher(smsContentDefine);
            while (matcher.find()) {
                String res = matcher.group();
                // 去掉${***}前后缀后的占位属性值
                smsContentParamList.add(res.substring(2, res.length() - 1));
            }

            // 手机号列表
            List<String> phoneList;
            // 个性化短信内容列表
            List<String> contentList;
            // 若短信存在占位内容，则需依次替换为个性化短信内容
            if (!CollectionUtils.isEmpty(smsContentParamList)) {
                contentList = Lists.newArrayList();
                phoneList = Lists.newArrayList();
                targetUserListYes.stream().forEach(memberDetailDTO -> {
                    contentList.add(this.replacePatternStrs(smsContentDefine, smsContentParamList, memberDetailDTO));
                    phoneList.add(memberDetailDTO.getPhone());
                });
            }
            else {
                contentList = null;
                phoneList = targetUserListYes.parallelStream()
                    .filter(memberDetailDTO -> StringUtil.isNotNull(memberDetailDTO.getPhone()))
                    .map(memberDetailDTO -> memberDetailDTO.getPhone()).collect(Collectors.toList());
            }

            // 需要抄送的短信号（可以为空，多个短信号以逗号分隔）
            String phoneCopyStr = actionParamObj.getString("copy");
            if (StringUtil.isNotNull(phoneCopyStr)) {
                if (CollectionUtils.isEmpty(contentList)) {
                    Arrays.stream(phoneCopyStr.split(",")).filter(copyPhone -> StringUtil.isNotNull(copyPhone))
                        .forEach(copyPhone -> {
                            phoneList.add(copyPhone);
                        });
                }
                else {
                    Arrays.stream(phoneCopyStr.split(",")).filter(copyPhone -> StringUtil.isNotNull(copyPhone))
                        .forEach(copyPhone -> {
                            contentList.add(this.replacePatternStrs(smsContentDefine, smsContentParamList, null));
                            phoneList.add(copyPhone);
                        });
                }
            }

            // 批量发送短信
            SmsUtil smsUtil = SpringContextUtils.getBean(SmsUtil.class);
            if (CollectionUtils.isEmpty(contentList)) {
                // 发送统一内容短信
                smsUtil.batchSendSms(smsContentDefine, phoneList);
            }
            else {
                // 发送个性化内容短信
                smsUtil.batchSendDynamicsSms(contentList, phoneList);
            }
        }
        catch (Exception e) {
            logger.info("SendSmsActionHandler.handler error! msg={} ", e.getMessage());
        }
    }

    @Override
    public void doBusiness(DelegateExecution delegateExecution, String nodeJsonParam) {
        // String nodeJsonParam = (String) param.getValue(delegateExecution);
        List<TargetUserDTO> targetUserListYes = (List<TargetUserDTO>) delegateExecution
            .getVariable("targetUserListYes");
        this.handler(nodeJsonParam, targetUserListYes);
    }

    // 将字符串中的占位符属性内容，替换为指定对象相应的属性值
    private String replacePatternStrs(String targetStr, List<String> patternFields, Object obj) {
        String result = targetStr;
        for (String field : patternFields) {
            result = result.replace("${".concat(field).concat("}"),
                obj == null ? "" : (String) ReflectUtil.getFieldValue(obj, field));
        }
        return result;
    }

    @Override
    public String getNodeType() {
        return MarketingNodeExecuteTypeEnum.SENDSMS.getCode();
    }
}
