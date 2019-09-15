package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.MockitoTest;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.OrderServiceImpl;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.OrderProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.OrderDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.ReverseOrderDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.OrderQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.ReverseOrderQuery;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * 订单单元测试
 */
public class OrderControllerTest extends MockitoTest {

    @Autowired
    private OrderController orderController;

    @Mock
    private OrderProxy orderProxy;
    @InjectMocks
    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void queryReverseListByConditionTest() {
        Long orderNo = 100455L;
        ReverseOrderQuery reverseCondition = new ReverseOrderQuery();
        reverseCondition.setBuyerId(1L);

        ResponseMsg<List<ReverseOrderDTO>> responseMsg = new ResponseMsg<>();
        List<ReverseOrderDTO> list = new ArrayList<>();
        ReverseOrderDTO reverseOrderDTO = new ReverseOrderDTO();
        reverseOrderDTO.setOrderNo(orderNo);
        list.add(reverseOrderDTO);
        responseMsg.setData(list);

        when(orderProxy.queryReverseListByCondition(reverseCondition)).thenReturn(responseMsg);

        ResponseMsg<List<ReverseOrderDTO>> rtn = orderController.queryReverseListByCondition(reverseCondition);
        List<ReverseOrderDTO> rtnList = rtn.getData();
        assertTrue(rtnList.get(0).getOrderNo().longValue() == orderNo.longValue());

    }

    @Test
    public void queryOrderListByConditionTest() {
        Long orderNo = 100455L;
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setOrderNo(orderNo);

        ResponseMsg<List<OrderDTO>> responseMsg = new ResponseMsg<>();
        List<OrderDTO> list = new ArrayList<>();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderNo(orderNo);
        list.add(orderDTO);
        responseMsg.setData(list);

        when(orderProxy.queryOrderListByCondition(orderQuery)).thenReturn(responseMsg);

        ResponseMsg<List<OrderDTO>> rtn = orderController.queryOrderListByCondition(orderQuery);
        List<OrderDTO> rtnList = rtn.getData();
        assertTrue(rtnList.get(0).getOrderNo().longValue() == orderNo.longValue());
    }

    @Test
    public void countLatestConsumeTest() {
        Long memberId = 1L;
        Long orderNo = 100455L;
        ResponseMsg<OrderDTO> responseMsg = new ResponseMsg<>();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderNo(orderNo);
        responseMsg.setData(orderDTO);

        when(orderProxy.countLatestConsume(memberId)).thenReturn(responseMsg);

        ResponseMsg<OrderDTO> rtn = orderController.countLatestConsume(memberId);
        assertTrue(rtn.getData().getOrderNo().longValue() == orderNo.longValue());
    }

    @Test
    public void countMemberConsumeTest() {
        Long memberId = 100455L;
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setData(memberId);
        when(orderProxy.countMemberConsume(memberId)).thenReturn(responseMsg);

        ResponseMsg rtn = orderController.countMemberConsume(memberId);
        assertTrue(rtn.getData().toString().equals(memberId.toString()));
    }

}
