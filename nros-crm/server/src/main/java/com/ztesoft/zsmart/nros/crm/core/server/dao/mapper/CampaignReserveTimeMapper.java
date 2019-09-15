package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper;

import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignReserveTimeDO;

import java.util.List;

/**
 * 报名活动可预约时间段
 *
 * @author fan.chaolin
 * @date 2019/4/13
 */
public interface CampaignReserveTimeMapper {
    /**
     * 通过活动id删除数据库记录
     *
     * @param campaignId
     * @return
     */
    int deleteByCampaignId(Long campaignId);

    /**
     * 根据campaignId查询预约时间段
     *
     * @param campaignReserveTimeDO
     * @return
     */
    List<CampaignReserveTimeDO> selectBycampaignId(CampaignReserveTimeDO campaignReserveTimeDO);
}
