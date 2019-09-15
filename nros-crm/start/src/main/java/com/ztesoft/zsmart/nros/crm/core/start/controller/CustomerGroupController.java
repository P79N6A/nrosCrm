package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.CustomerGroupService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.TagConfigDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 标签controller
 *
 * @author lvjunyi
 */
@SessionController
@RequestMapping("/customer-group")
@Api(value = "标签管理", tags = "标签管理")
public class CustomerGroupController {

    @Autowired
    private CustomerGroupService customerGroupService;

    /**
     * 标签列表查询--根据输入(无输入则查询所有)
     *
     * @return
     */
    @PostMapping("/qy-tag-config-list")
    @ApiOperation("客群列表查询")
    public ResponseMsg<List<TagConfigDTO>> queryTagConfigList(@RequestBody TagConfigQuery tagConfigQuery) {
        return customerGroupService.qyTagConfigList(tagConfigQuery);
    }

    /**
     * 新增标签
     *
     * @return
     */
    @PostMapping("/tag-save")
    @ApiOperation("新增标签")
    public ResponseMsg<?> saveTag(@RequestBody TagConfigParams tagConfigParams) {
        return customerGroupService.saveTag(tagConfigParams);
    }

    /**
     * 删除标签
     *
     * @return
     */
    @DeleteMapping("/tag-del")
    @ApiOperation("删除标签")
    public ResponseMsg<?> delTag(@RequestBody TagConfigParams tagConfigParams) {
        return customerGroupService.delTag(tagConfigParams);
    }

    /**
     * 修改标签
     *
     * @return
     */
    @PostMapping("/tag-modify")
    @ApiOperation("修改标签")
    public ResponseMsg<?> modifyTag(@RequestBody TagConfigParams tagConfigParams) {
        return customerGroupService.modifyTag(tagConfigParams);
    }

    /**
     * 标签详情查询
     *
     * @return
     */
    @GetMapping("/detail")
    @ApiOperation("标签详情查询")
    ResponseMsg<TagConfigDTO> queryTagById(@RequestParam Long id) {
        return customerGroupService.queryTagById(id);
    }


    /**
     * 标签会员列表展示
     *
     * @return
     */
    @PostMapping("/qry-members-by-tagid")
    @ApiOperation("根据标签id查询会员列表")
    public ResponseMsg<List<MemberDTO>> queryMenbersListById(@RequestBody TagQuery tagQuery) {
        return customerGroupService.queryMenbersListById(tagQuery);
    }


}
