package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.SaveCampaignServiceParam;
import com.ztesoft.zsmart.nros.crm.core.server.common.convertor.CampaignConvertor;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.dataobject.generator.CampaignServiceDO;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignServiceMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.CampaignStatisticMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.SignCampaignMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.CampaignDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.dao.mapper.generator.CampaignServiceDOMapper;
import com.ztesoft.zsmart.nros.crm.core.server.repository.CampaignRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author yuanxiaokai
 * @date 2019/7/9
 */
public class CampaignApiControllerTest extends MockitoTest {

    @Autowired
    private CampaignApiController campaignApiController;

    @Autowired
    @InjectMocks
    private CampaignRepository campaignRepository;

    @Mock
    private CampaignDOMapper campaignDOMapper;

    @Mock
    private SignCampaignMapper signCampaignMapper;

    @Mock
    private CampaignServiceDOMapper campaignServiceDOMapper;

    @Mock
    private CampaignStatisticMapper campaignStatisticMapper;

    @Mock
    private CampaignServiceMapper campaignServiceMapper;

    @Test
    public void testShareCampaignById() {
        CampaignDO campaignDO = new CampaignDO();
        campaignDO.setSubmitTitle("test");
        Mockito.when(campaignDOMapper.selectByPrimaryKey(1L)).thenReturn(campaignDO);
        Assert.assertEquals(CampaignConvertor.INSTANCE.doToDTO(campaignDO),
            campaignApiController.shareCampaignById(1L).getData());
    }

    @Test
    public void testSignUp() {
        SaveCampaignServiceParam saveCampaignServiceParam = new SaveCampaignServiceParam();
        saveCampaignServiceParam.setCampaignId(17L);
        saveCampaignServiceParam.setSignName("test2");
        saveCampaignServiceParam.setSignPhone("17788996652");
        Mockito.when(signCampaignMapper.queryIsSignInOpen(17L)).thenReturn("1");
        Mockito.when(campaignServiceDOMapper.insert(any(CampaignServiceDO.class))).thenReturn(1);
        Mockito.doNothing().when(campaignStatisticMapper).updateSignUpStatistic(1L);
        Assert.assertNotNull(campaignApiController.signUp(saveCampaignServiceParam).getData());
    }

    @Test
    public void testSignIn() {
        SaveCampaignServiceParam saveCampaignServiceParam = new SaveCampaignServiceParam();
        saveCampaignServiceParam.setCampaignId(89L);
        saveCampaignServiceParam.setSignName("test");
        saveCampaignServiceParam.setSignPhone("12345");
        saveCampaignServiceParam.setSignInCode("250349");
        CampaignServiceDO campaignServiceDO = new CampaignServiceDO();
        campaignServiceDO.setSignInCode("250349");
        Mockito.when(campaignServiceMapper.selectBySignPhone(any(CampaignServiceDO.class)))
            .thenReturn(campaignServiceDO);
        Mockito.when(signCampaignMapper.queryIsSignInOpen(89L)).thenReturn("1");
        Mockito.when(campaignServiceDOMapper.updateByPrimaryKeySelective(any(CampaignServiceDO.class))).thenReturn(1);
        Mockito.doNothing().when(campaignStatisticMapper).updateSignUpStatistic(any());
        Assert.assertNotNull(campaignApiController.signIn(saveCampaignServiceParam).getData());
    }
}
