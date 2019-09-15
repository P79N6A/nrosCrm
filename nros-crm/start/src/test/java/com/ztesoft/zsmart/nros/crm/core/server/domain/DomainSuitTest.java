package com.ztesoft.zsmart.nros.crm.core.server.domain;

import com.ztesoft.zsmart.nros.crm.core.server.domain.eventtrace.EventTraceDomainTest;
import com.ztesoft.zsmart.nros.crm.core.server.domain.marketing.MarketingDomainTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author yuanxiaokai
 * @date 2019/7/16
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    MarketingDomainTest.class, EventTraceDomainTest.class
})
public class DomainSuitTest {
}
