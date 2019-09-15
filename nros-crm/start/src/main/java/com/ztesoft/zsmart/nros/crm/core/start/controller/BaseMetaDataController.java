package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.base.util.CommonFunctions;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.BaseMetaDataService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.EnumConfigDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author yuan
 * @date 2019/7/23
 */
@SessionController
@RequestMapping("/baseMeta")
@Api(value = "基础数据", tags = {
    "基础数据"
})
public class BaseMetaDataController {

    @Autowired
    private BaseMetaDataService baseMetaDataService;

    @GetMapping("/list-channel-config")
    @ApiOperation("查询优惠券渠道")
    public ResponseMsg<List<EnumConfigDTO>> listChannelEnumConfig() {
        return baseMetaDataService.listChannelEnumConfig();
    }

    /**
     * 查询下级区域列表（不传查询所有顶级区域）
     * 
     * @param parentId 上级id
     * @return 区域集合
     */
    @ApiOperation(value = "查询下级区域列表", notes = "查询下级区域列表（不传查询所有顶级区域）")
    @RequestMapping(value = "/parent", method = RequestMethod.GET)
    public ResponseMsg queryAreaList(@RequestParam(required = false) Long parentId) {
        return CommonFunctions.runSupplierByList(() -> baseMetaDataService.queryAreaList(parentId), "查询区域列表异常");
    }

}
