package com.ztesoft.zsmart.nros.crm.core.server;

import com.ztesoft.zsmart.nros.crm.core.server.common.util.QrCodeUtilTest;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.DaoSuitTest;
import com.ztesoft.zsmart.nros.crm.core.server.domain.DomainSuitTest;
import com.ztesoft.zsmart.nros.crm.core.server.job.CampaignStatisticJobTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.MiddlewareSuitTest;
import com.ztesoft.zsmart.nros.crm.core.server.repository.RepositorySuitTest;
import com.ztesoft.zsmart.nros.crm.core.server.service.ServiceSuitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author yuanxiaokai
 * @date 2019/7/17
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ServiceSuitTest.class, RepositorySuitTest.class, QrCodeUtilTest.class, DaoSuitTest.class, DomainSuitTest.class,
    CampaignStatisticJobTest.class, MiddlewareSuitTest.class
})
public class ServerSuitTest {
}
