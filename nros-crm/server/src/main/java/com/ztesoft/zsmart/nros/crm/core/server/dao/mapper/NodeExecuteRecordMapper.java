package com.ztesoft.zsmart.nros.crm.core.server.dao.mapper;

import java.util.List;

import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingInstanceQuery;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.NodeExecuteRecordDO;

/**
 * 自定义流程节点执行记录Mapper
 * 
 * @author 浩鲸云计算科技股份有限公司
 * @date 2019-07-24
 */
public interface NodeExecuteRecordMapper {
    /**
     * 查询流程节点执行实例
     *
     * @param marketingInstanceQuery
     * @return int
     */
    List<NodeExecuteRecordDO> selectInstance(MarketingInstanceQuery marketingInstanceQuery);
}