package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch;

import java.util.List;
import java.util.stream.Collectors;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums.MarketingNodeExecuteTypeEnum;
import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.base.util.SpringContextUtils;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.CouponService;

/**
 * 优惠券核销 控制节点
 *
 * @creator:huanggp
 * @since:2018-06-19 16:43
 */
@Component
public class CouponCancelJudgeNodeHandler extends BaseJudgeNodeHandler {
    private transient Logger logger = LoggerFactory.getLogger(BaseJudgeNodeHandler.class);

    /**
     * 是否核销优惠券
     */
    @Override
    public List<TargetUserDTO> operate(DelegateExecution delegateExecution, String innerParam) throws BusiException {
        try {
            // String innerParam = (String) param.getValue(delegateExecution);
            JSONObject jsonParam = JSONObject.parseObject(innerParam);
            String couponCode = jsonParam.getString("couponCode");
            if (StringUtils.isEmpty(couponCode)) {
                logger.warn("CouponCancelJudgeFlowNode.operate() error! couponCode(selectedId) is empty!!");
                return null;
            }
            // 根据优惠券id 获取核销成功的客群
            CouponService couponService = SpringContextUtils.getBean(CouponService.class);
            List<TargetUserDTO> targetUserListAll = couponService.listConsumeMember(couponCode).getData()
                .parallelStream().map(item -> {
                    TargetUserDTO memberDetailDTO = new TargetUserDTO();
                    memberDetailDTO.setId(item.getMemberId());
                    memberDetailDTO.setName(item.getMemberName());
                    return memberDetailDTO;
                }).distinct().collect(Collectors.toList());
            logger.info("CouponCancelJudgeFlowNode.operate(),targetUserListAll={}", targetUserListAll);
            return targetUserListAll;
        }
        catch (Exception e) {
            logger.error("CouponCancelJudgeFlowNode.operate() error!  msg={}", e.getMessage());
            return null;
        }
    }

    @Override
    public String getNodeType() {
        return MarketingNodeExecuteTypeEnum.COUPON_CANCEL.getCode();
    }
}
