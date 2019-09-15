package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.TagConfigDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagDeleteParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 会员中心接口订阅
 *
 * @author lvjunyi
 */
@FeignClient(value = "member")
public interface CustomerGroupProxy {
    /**
     * 标签列表查询
     *
     * @param tagConfigQuery
     * @return
     */
    @GetMapping(value = "/nrosapi/member/v1/tag-config/find-tag-config-list")
    ResponseMsg<List<TagConfigDTO>> qyTagConfigList(@RequestBody TagConfigQuery tagConfigQuery);

    /**
     * 保存标签
     *
     * @param tagConfigParams
     * @return
     */
    @PostMapping(value = "/nrosapi/member/v1/tag-config/save-tag-config")
    ResponseMsg saveTag(TagConfigParams tagConfigParams);

    /**
     * 删除标签
     *
     * @param tagParams
     * @return
     */
    @DeleteMapping(value = "/nrosapi/member/v1/tag-config/delete-tag")
    ResponseMsg delTag(TagDeleteParams tagParams);

    /**
     * 编辑标签
     *
     * @param tagConfigParams
     * @return
     */
    @PostMapping(value = "/nrosapi/member/v1/tag-config/modify-tag-config")
    ResponseMsg modifyTag(TagConfigParams tagConfigParams);

    /**
     * 通过id查询详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/nrosapi/member/v1/tag-config/find-tag-config-detail")
    ResponseMsg<TagConfigDTO> queryTagById(@RequestParam("id") Long id);

    /**
     * 通过id查询会员列表
     *
     * @param tagQuery
     * @return
     */
    @PostMapping(value = "/nrosapi/member/v1/tag/find-member-by-tag")
    ResponseMsg<List<MemberDTO>> queryMenbersListById(@RequestBody TagQuery tagQuery);

}