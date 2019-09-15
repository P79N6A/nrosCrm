package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.AreaDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.EnumConfigDTO;

import java.util.List;

/**
 * 基础数据中心远程调用接口定义
 *
 * @author litao
 * @date 2019/6/18
 */
public interface BaseMetaDataService {

    /**
     * 渠道列表获取
     * 
     * @return ResponseMsg
     */
    ResponseMsg<List<EnumConfigDTO>> listChannelEnumConfig();

    /**
     * 查询所有的区域信息
     *
     * @param parentId 父区域ID
     * @return 返回区域的DTO（AreaDto的列表）
     */
    List<AreaDTO> queryAreaList(Long parentId);
}
