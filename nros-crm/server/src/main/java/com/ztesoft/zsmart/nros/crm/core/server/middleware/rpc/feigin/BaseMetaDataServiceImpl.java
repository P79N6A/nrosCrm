package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.BaseMetaDataService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.BaseMetaDataProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.AreaDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.EnumConfigDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 渠道列表获取
 * 
 * @author yuan
 * @date 2019/7/23
 */
@Service
@Slf4j
public class BaseMetaDataServiceImpl implements BaseMetaDataService {

    @Autowired
    private BaseMetaDataProxy baseMetaDataProxy;

    @Override
    public ResponseMsg<List<EnumConfigDTO>> listChannelEnumConfig() {
        log.info("start to impl BaseMetaDataServiceImpl...");
        return baseMetaDataProxy.listChannelEnumConfig();
    }

    @Override
    public List<AreaDTO> queryAreaList(Long parentId) {
        return baseMetaDataProxy.queryAreaList(parentId).getData();
    }
}
