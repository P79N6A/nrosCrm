package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc;

import java.util.List;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.TagConfigDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 标签服务远程调用接口定义
 * 
 * @author lvjunyi
 */
public interface CustomerGroupService {

    /**
     * 标签列表查询
     * 
     * @param tagConfigQuery
     * @return
     */
    ResponseMsg<List<TagConfigDTO>> qyTagConfigList(TagConfigQuery tagConfigQuery);

    /**
     * 新增标签
     * 
     * @param tagConfigParams
     * @return
     */
    ResponseMsg<?> saveTag(@RequestBody TagConfigParams tagConfigParams);

    /**
     * 删除标签
     *
     * @param tagConfigParams
     * @return
     */
    ResponseMsg<?> delTag(@RequestBody TagConfigParams tagConfigParams);

    /**
     * 修改标签
     *
     * @param tagConfigParams
     * @return
     */
    ResponseMsg<?> modifyTag(@RequestBody TagConfigParams tagConfigParams);

    /**
     * 通过标签d查询详情
     * 
     * @param id
     * @return
     */
    ResponseMsg<TagConfigDTO> queryTagById(@RequestParam Long id);

    /**
     * 标签会员列表查询
     *
     * @param tagQuery
     * @return
     */
    ResponseMsg<List<MemberDTO>> queryMenbersListById(@RequestBody TagQuery tagQuery);

}
