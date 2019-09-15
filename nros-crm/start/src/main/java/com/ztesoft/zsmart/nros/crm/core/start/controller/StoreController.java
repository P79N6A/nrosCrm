package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.StoreService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.StoreDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.StoreQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: yaodingw
 * Date: 2019-04-17
 * Time: 19:08
 */
@SessionController
@RequestMapping("/org/store")
@Api(value = "门店信息管理", tags = {"门店信息管理"})
public class StoreController {

    @Autowired
    private StoreService storeService;

    /**
     * @Description 查询门店列表
     * @Author yangshaoxin
     * @Date 2019/4/24 10:36
     * @Param [storeQuery]
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg<com.github.pagehelper.PageInfo<com.ztesoft.zsmart.nros.sbc.crm.cloud.store.model.StoreDTO>>
     **/
    @GetMapping
    @ApiOperation(value = "查询门店列表", notes = "查询门店列表", response = StoreDTO.class)
    public ResponseMsg queryStoreByParams(StoreQuery storeQuery) {
        return storeService.queryStoreByParams(storeQuery);
    }

    /**
     * @Description 查询门店详情
     * @Author yangshaoxin
     * @Date 2019/4/24 10:36
     * @Param [id]
     * @return com.ztesoft.zsmart.nros.base.model.ResponseMsg<com.ztesoft.zsmart.nros.sbc.crm.cloud.store.model.StoreDTO>
     **/
    @GetMapping("/{id}")
    @ApiOperation(value = "查询门店详情", notes = "查询门店详情", response = StoreDTO.class)
    public ResponseMsg<StoreDTO> queryStoreDetailByParams(@PathVariable("id") Long id) {
        return storeService.queryStoreDetailByParams(id);
    }

}
