package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper;

import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.MarketingDefineDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 营销自定义Mapper
 * 
 * @author fan.chaolin
 * @date 2019/4/18
 */
public interface MarketingDefineMapper {

    /**
     * 营销流程定义列表查询
     * 
     * @param marketingDefineDO
     * @return
     */
    List<MarketingDefineDO> queryList(MarketingDefineDO marketingDefineDO);

    /**
     * 营销流程定义查询
     * 
     * @param merchantCode
     * @param marketingType
     * @return
     */
    List<MarketingDefineDO> selectActiveCampaignDefines(@Param("merchantCode") String merchantCode,
        @Param("marketingType") String marketingType);

    /**
     * 营销流程分析设置：包括有效期、触发频率、分析周期等
     *
     * @param record record
     * @return int
     */
    int setAnalysis(MarketingDefineDO record);

}
