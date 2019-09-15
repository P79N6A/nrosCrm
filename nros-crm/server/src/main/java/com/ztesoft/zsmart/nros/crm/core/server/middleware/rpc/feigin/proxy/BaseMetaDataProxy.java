package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.AreaDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.EnumConfigDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author yuan
 * @date 2019/7/23
 */
@FeignClient(value = "basedata")
public interface BaseMetaDataProxy {

    /**
     * 查询渠道种类
     * 
     * @return ResponseMsg
     */
    @RequestMapping(value = "/nrosapi/basedata/v1/metadata/channel-type", method = RequestMethod.GET)
    ResponseMsg<List<EnumConfigDTO>> listChannelEnumConfig();

    /**
     * 查询下级区域列表（不传查询所有顶级区域）
     *
     * @param parentId 上级id
     * @return 区域集合
     */
    @RequestMapping(value = "/nrosapi/basedata/v1/area/parent", method = RequestMethod.GET)
    ResponseMsg<List<AreaDTO>> queryAreaList(@RequestParam(value = "parentId", required = false) Long parentId);

}
