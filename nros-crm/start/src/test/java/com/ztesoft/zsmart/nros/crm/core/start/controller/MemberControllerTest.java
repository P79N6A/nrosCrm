package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.GrowthRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.TagMemberParam;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.MemberServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.MemberProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.BalanceRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberBalanceDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberDetailDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.MemberListDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.PointUseRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.TagConfigDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.TagDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.BalanceRecordQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.GrowthRecordQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.MemberQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.PointRecordQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagConfigQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagParams;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.TagQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.vo.MemberPointVO;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

/**
 * 会员接口单元测试
 */
public class MemberControllerTest extends MockitoTest {

    @Autowired
    private MemberController memberController;

    @Mock
    private MemberProxy memberProxy;

    @InjectMocks
    @Autowired
    private MemberServiceImpl memberService;

    @Test
    public void listMemberTest() {
        MemberQuery memberQuery = new MemberQuery();

        ResponseMsg<List<MemberListDTO>> responseMsg = new ResponseMsg<>();
        List<MemberListDTO> list = new ArrayList<>();
        MemberListDTO memberListDTO = new MemberListDTO();
        memberListDTO.setName("zhangsan");
        list.add(memberListDTO);
        responseMsg.setData(list);

        when(memberProxy.queryMemberList(memberQuery)).thenReturn(responseMsg);

        ResponseMsg<List<MemberListDTO>> rtn = memberController.listMember(memberQuery);
        assertTrue(rtn.getData().get(0).getName().equals("zhangsan"));
    }

    @Test
    public void tagsMembersTest() {
        TagMemberParam tagMemberParam = new TagMemberParam();
        List<String> memberIdList = new ArrayList<>();
        memberIdList.add("1");
        List<String> tagIdList = new ArrayList<>();
        tagIdList.add("2");
        tagMemberParam.setMemberIdList(memberIdList);
        tagMemberParam.setTagIdList(tagIdList);
        tagMemberParam.setTagType("1");

        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setSuccess(true);

        when(memberProxy.saveMemberTagBatch(anyList())).thenReturn(responseMsg);

        ResponseMsg rtn = memberController.tagsMembers(tagMemberParam);
        assertTrue(rtn.getSuccess() == true);
    }

    @Test
    public void deleteMemberTagTest() {
        TagParams tagParams = new TagParams();
        tagParams.setMemberId(1L);
        tagParams.setTagId(1L);

        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setSuccess(true);
        when(memberProxy.modifyMemberTag(tagParams)).thenReturn(responseMsg);

        ResponseMsg rtn = memberController.deleteMemberTag(tagParams);
        assertTrue(rtn.getSuccess() == true);
    }

    @Test
    public void getDetailByIdTest() {
        Long memberId = 1L;

        ResponseMsg<MemberDetailDTO> responseMsg = new ResponseMsg<>();
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
        memberDetailDTO.setName("zhangsan");
        responseMsg.setData(memberDetailDTO);
        when(memberProxy.getDetailById(memberId)).thenReturn(responseMsg);

        ResponseMsg<MemberDetailDTO> rtn = memberController.getDetailById(memberId);

        assertTrue("zhangsan".equals(rtn.getData().getName()));
    }

    @Test
    public void qyTagByMemberTest() {
        TagQuery query = new TagQuery();
        query.setTagId(1L);
        ResponseMsg<List<TagDTO>> responseMsg = new ResponseMsg<>();
        List<TagDTO> list = new ArrayList<>();
        TagDTO tagDTO = new TagDTO();
        tagDTO.setMemberId(1L);
        list.add(tagDTO);
        responseMsg.setData(list);
        when(memberProxy.qyTagByMember(query)).thenReturn(responseMsg);

        ResponseMsg<List<TagDTO>> rtn = memberController.qyTagByMember(query);

        assertTrue(rtn.getData().get(0).getMemberId().longValue() == 1);
    }

    @Test
    public void findPointUseRecordTest() {
        PointRecordQuery query = new PointRecordQuery();
        query.setMemberId(1L);

        ResponseMsg<List<PointUseRecordDTO>> responseMsg = new ResponseMsg<>();
        List<PointUseRecordDTO> list = new ArrayList<>();
        PointUseRecordDTO pointUseRecordDTO = new PointUseRecordDTO();
        pointUseRecordDTO.setUsePoint(5);
        list.add(pointUseRecordDTO);
        responseMsg.setData(list);

        when(memberProxy.findPointUseRecord(query)).thenReturn(responseMsg);

        ResponseMsg<List<PointUseRecordDTO>> rtn = memberController.findPointUseRecord(query);

        assertTrue(rtn.getData().get(0).getUsePoint() == 5);
    }

    @Test
    public void findPointRecordTest() {
        PointRecordQuery query = new PointRecordQuery();
        query.setMemberId(1L);

        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setSuccess(true);
        when(memberProxy.findPointRecord(query)).thenReturn(responseMsg);

        ResponseMsg rtn = memberController.findPointRecord(query);
        assertTrue(rtn.getSuccess() == true);

    }

    @Test
    public void findMemberBalanceTest() {
        Long memberId = 1L;
        ResponseMsg<MemberBalanceDTO> responseMsg = new ResponseMsg<>();
        MemberBalanceDTO balanceDTO = new MemberBalanceDTO();
        balanceDTO.setNormalBalance(500L);
        responseMsg.setData(balanceDTO);

        when(memberProxy.findMemberBalance(memberId)).thenReturn(responseMsg);

        ResponseMsg<MemberBalanceDTO> rtn = memberController.findMemberBalance(memberId);

        assertTrue(rtn.getData().getNormalBalance().longValue() == 500);
    }

    @Test
    public void findGrowthRecordTest() {
        GrowthRecordQuery query = new GrowthRecordQuery();
        query.setMemberId(1L);

        ResponseMsg<List<GrowthRecordDTO>> responseMsg = new ResponseMsg<>();
        List<GrowthRecordDTO> list = new ArrayList<>();
        GrowthRecordDTO growthRecordDTO = new GrowthRecordDTO();
        growthRecordDTO.setGrowth(100);
        list.add(growthRecordDTO);
        responseMsg.setData(list);

        when(memberProxy.findGrowthRecord(query)).thenReturn(responseMsg);

        ResponseMsg<List<GrowthRecordDTO>> rtn = memberController.findGrowthRecord(query);
        assertTrue(rtn.getData().get(0).getGrowth() == 100);
    }

    @Test
    public void findBalanceRecordListTest() {
        BalanceRecordQuery query = new BalanceRecordQuery();
        query.setMemberId(1L);

        ResponseMsg<List<BalanceRecordDTO>> responseMsg = new ResponseMsg<>();
        List<BalanceRecordDTO> list = new ArrayList<>();
        BalanceRecordDTO balanceRecordDTO = new BalanceRecordDTO();
        balanceRecordDTO.setGiftAmount("1000");
        list.add(balanceRecordDTO);
        responseMsg.setData(list);

        when(memberProxy.findBalanceRecordList(query)).thenReturn(responseMsg);

        ResponseMsg<List<BalanceRecordDTO>> rtn = memberController.findBalanceRecordList(query);
        assertTrue(rtn.getData().get(0).getGiftAmount().equals("1000"));
    }

    @Test
    public void findDetailPointTest() {
        Long memberId = 1L;

        ResponseMsg responseTotalPoint = new ResponseMsg();
        responseTotalPoint.setData(1000);
        ResponseMsg responseUsePoint = new ResponseMsg();
        responseUsePoint.setData(400);
        when(memberProxy.findTotalPoint(memberId)).thenReturn(responseTotalPoint);
        when(memberProxy.findTotalUsePoint(memberId)).thenReturn(responseUsePoint);

        ResponseMsg<MemberPointVO> responseMsg = memberController.findDetailPoint(memberId);
        assertTrue(responseMsg.getData().getSurplusPoint() == 600);
    }

    @Test
    public void qyTagConfigListTest() {
        TagConfigQuery query = new TagConfigQuery();
        ResponseMsg<List<TagConfigDTO>> responseMsg = new ResponseMsg<>();
        TagConfigDTO tagConfigDTO = new TagConfigDTO();
        tagConfigDTO.setValue("test");
        responseMsg.setData(Lists.newArrayList(tagConfigDTO));
        when(memberProxy.qyTagConfigList(query)).thenReturn(responseMsg);

        ResponseMsg<List<TagConfigDTO>> rtn = memberController.qyTagConfigList(query);
        assertTrue(rtn.getData().get(0).getValue().equals("test"));
    }

}
