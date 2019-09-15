package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.action;

import java.util.List;
import java.util.stream.Collectors;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums.MarketingNodeExecuteTypeEnum;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Lists;
import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.BaseFlowNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.MemberService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagParams;

/**
 * 执行打标签动作
 * 
 * @return
 * @author PQ
 * @date 2019/6/20
 */
@Component
public class TaggedActionHandler extends BaseFlowNodeHandler {
    // public class TaggedActionHandler implements JavaDelegate {

    private static Logger logger = LoggerFactory.getLogger(TaggedActionHandler.class);

    public void handler(String json, List<TargetUserDTO> targetUserListYes) {
        try {
            // logger.info("SendSmsActionHandler.nodeJsonParam=:{}", json);
            JSONObject actionParamObj = JSON.parseObject(json);
            JSONArray tagIds = actionParamObj.getJSONArray("tagIds");
            // 校验是否存在打标参数
            if (null == tagIds) {
                logger.info("SendSmsActionHandler.handler()……tagIds param incorrect!!");
                return;
            }

            // 拼接参数
            List<TagParams> tagParamsList = Lists.newArrayList();
            // tagType:1手动打标；2自动打标
            String tagType = "1";
            for (int i = 0; i < tagIds.size(); i++) {
                Long tmpTagId = tagIds.getLong(i);
                tagParamsList.addAll(targetUserListYes.parallelStream().map(memberDetailDTO -> {
                    TagParams tagParams = new TagParams();
                    tagParams.setMemberId(memberDetailDTO.getId());
                    tagParams.setTagId(tmpTagId);
                    tagParams.setTagType(tagType);
                    return tagParams;
                }).collect(Collectors.toList()));
            }

            // 调用打标签接口
            MemberService memberService = SpringContextUtils.getBean(MemberService.class);
            memberService.saveMemberTagBatch(tagParamsList);
        }
        catch (Exception e) {
            logger.info("SendCouponActionHandler.handler error! msg={} ", e.getMessage());
        }

    }

    @Override
    public void doBusiness(DelegateExecution delegateExecution, String nodeJsonParam) {
        // String nodeJsonParam = (String) param.getValue(delegateExecution);
        List<TargetUserDTO> targetUserListYes = (List<TargetUserDTO>) delegateExecution
            .getVariable("targetUserListYes");
        this.handler(nodeJsonParam, targetUserListYes);
    }

    @Override
    public String getNodeType() {
        return MarketingNodeExecuteTypeEnum.ADDTAG.getCode();
    }
}
