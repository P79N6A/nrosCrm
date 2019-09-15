package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin;

import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.CustomerGroupService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.CustomerGroupProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.TagConfigDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagDeleteParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 标签服务远程调用实现类
 *
 * @author lvjunyi
 */
@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {

    @Autowired
    private CustomerGroupProxy customerGroupProxy;

    @Override
    public ResponseMsg<List<TagConfigDTO>> qyTagConfigList(TagConfigQuery tagConfigQuery) {
        return customerGroupProxy.qyTagConfigList(tagConfigQuery);
    }

    @Override
    public ResponseMsg saveTag(TagConfigParams tagConfigParams) {
        String tagName = tagConfigParams.getValue();
        if (StringUtils.isNotEmpty(tagName)) {
            TagConfigQuery tagConfigQuery = new TagConfigQuery();
            tagConfigQuery.setPageIndex(1);
            tagConfigQuery.setPageSize(10);
            tagConfigQuery.setValue(tagName);
            ResponseMsg<List<TagConfigDTO>> listResponseMsg = customerGroupProxy.qyTagConfigList(tagConfigQuery);
            if (listResponseMsg != null) {
                List<TagConfigDTO> list = listResponseMsg.getData();
                if (!CollectionUtils.isEmpty(list)) {
                    ExceptionHandler.publish("NROS-SBC-CRM-MEMBER-0002");
                }
            }
        }

        return customerGroupProxy.saveTag(tagConfigParams);
    }

    @Override
    public ResponseMsg delTag(TagConfigParams tagConfigParams) {
        TagDeleteParams tagParams = new TagDeleteParams();
        tagParams.setTagId(tagConfigParams.getId());
        return customerGroupProxy.delTag(tagParams);
    }

    @Override
    public ResponseMsg modifyTag(TagConfigParams tagConfigParams) {
        return customerGroupProxy.modifyTag(tagConfigParams);
    }


    @Override
    public ResponseMsg<TagConfigDTO> queryTagById(Long id) {
        return customerGroupProxy.queryTagById(id);
    }

    @Override
    public ResponseMsg<List<MemberDTO>> queryMenbersListById(TagQuery tagQuery) {
        return customerGroupProxy.queryMenbersListById(tagQuery);
    }

}
