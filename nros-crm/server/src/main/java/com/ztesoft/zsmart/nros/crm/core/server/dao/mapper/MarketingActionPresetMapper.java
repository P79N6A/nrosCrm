package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper;

import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.MarketingActionPresetDO;

/**
 * 营销流程动作预制自定义Mapper
 *
 * @author PQ
 * @date 2019/4/19
*/
public interface MarketingActionPresetMapper {
    /**
     * 根据营销动作类型，查找动作预制记录
     *
     * @param actionType 动作类型：:[0]发短信,[3]赠送积分,[4]添加优惠券,[6]打标签
     * @return MarketingActionPresetDO
     * @author PQ
     * @date 2019/4/19
    */
    MarketingActionPresetDO selectByActionType(String actionType);
}