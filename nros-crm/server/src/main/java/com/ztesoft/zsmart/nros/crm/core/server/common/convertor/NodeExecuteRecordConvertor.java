package com.ztesoft.zsmart.nros.crm.core.server.common.convertor;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ztesoft.zsmart.nros.base.convertor.IConvertor;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.NodeExecuteRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.NodeExecuteRecordParam;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.NodeExecuteRecordDO;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.model.NodeExecuteRecordBO;

/**
 * 活动节点执行记录表转换类
 * 
 * @author PQ
 * @date 2019/7/24
 */
@Mapper
public interface NodeExecuteRecordConvertor
    extends IConvertor<NodeExecuteRecordParam, Object, NodeExecuteRecordDTO, NodeExecuteRecordBO, NodeExecuteRecordDO> {
    NodeExecuteRecordConvertor INSTANCE = Mappers.getMapper(NodeExecuteRecordConvertor.class);

    /**
     * DOList转DTOList
     * 
     * @param nodeExecuteRecordDOList
     * @return
     */
    List<NodeExecuteRecordDTO> doDOListToDTO(List<NodeExecuteRecordDO> nodeExecuteRecordDOList);
}
