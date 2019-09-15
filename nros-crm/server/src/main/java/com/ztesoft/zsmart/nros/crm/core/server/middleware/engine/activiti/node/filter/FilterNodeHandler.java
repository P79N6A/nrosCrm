package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.filter;

import java.util.List;
import java.util.stream.Collectors;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums.MarketingNodeExecuteTypeEnum;
import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.BaseFlowNodeHandler;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.MemberService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberListDTO;

/**
 * 客群节点，筛选处理
 * 
 * @author PQ
 * @date 2019/6/6
 */
@Component
public class FilterNodeHandler extends BaseFlowNodeHandler {
    // public class FilterFlowNode implements JavaDelegate {

    private static Logger logger = LoggerFactory.getLogger(FilterNodeHandler.class);

    @Override
    public void doBusiness(DelegateExecution delegateExecution, String innerParam) {
        List<TargetUserDTO> targetUserList = this.filterUser(innerParam);
        delegateExecution.setVariable("targetUserListYes", targetUserList);
        delegateExecution.setVariable("hasTargetUserYes", !CollectionUtils.isEmpty(targetUserList));
    }

    /**
     * 过滤客群
     * 
     * @param innerJsonParam
     * @return java.util.List<MemberDetailDTO>
     * @author PQ
     * @date 2019/7/12
     */
    public List<TargetUserDTO> filterUser(String innerJsonParam) {
        List<TargetUserDTO> targetUserList = com.google.common.collect.Lists.newArrayList();
        try {
            // 筛选客群参数
            JSONObject jsonParam = JSONObject.parseObject(innerJsonParam);
            Long tagId = jsonParam.getLong("groupId");
            if (null == tagId) {
                logger.error("FilterFlowNode.filterUser() tagId is empty!! ");
            }
            else {
                MemberService memberService = SpringContextUtils.getBean(MemberService.class);
                List<MemberListDTO> memberListDTOList = memberService.queryMemberByTag(tagId).getData();
                if (!org.springframework.util.CollectionUtils.isEmpty(memberListDTOList)) {
                    targetUserList = memberListDTOList.parallelStream().map(memberListDTO -> {
                        TargetUserDTO memberDetailDTO = new TargetUserDTO();
                        memberDetailDTO.setId(memberListDTO.getId());
                        memberDetailDTO.setName(memberListDTO.getName());
                        memberDetailDTO.setPhone(memberListDTO.getPhone());
                        return memberDetailDTO;
                    }).collect(Collectors.toList());
                }
            }
        }
        catch (Exception e) {
            logger.error("FilterFlowNode.execute() error! msg={} ", e.getMessage());
        }
        finally {
            return targetUserList;
        }
    }

    @Override
    public String getNodeType() {
        return MarketingNodeExecuteTypeEnum.FILTER.getCode();
    }
}
