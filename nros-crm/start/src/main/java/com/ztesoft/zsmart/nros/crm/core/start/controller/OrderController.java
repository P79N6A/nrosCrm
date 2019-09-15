package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.OrderService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.OrderDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.ReverseOrderDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.OrderQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.ReverseOrderQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@SessionController
@RequestMapping("/order")
@Api(value = "订单远程调用", tags = {"订单远程调用"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 查询会员退单记录
     * @param reverseCondition
     * @return
     */
    @PostMapping(value = "/query-reverse-list")
    @ApiOperation("查询会员退单记录")
    public ResponseMsg<List<ReverseOrderDTO>> queryReverseListByCondition(@RequestBody ReverseOrderQuery reverseCondition) {
        return orderService.queryReverseListByCondition(reverseCondition);
    }

    /**
     * 查询会员订单记录
     */
    @PostMapping(value = "/query-order-list")
    @ApiOperation("查询会员订单记录")
    public ResponseMsg<List<OrderDTO>> queryOrderListByCondition(@RequestBody OrderQuery orderQuery) {
        return orderService.queryOrderListByCondition(orderQuery);
    }

    /**
     * 查询会员最后一次消费记录
     * @param memberId
     * @return
     */
    @GetMapping(value = "/count-latest-consume")
    @ApiOperation("查询会员最后一次消费记录")
    public ResponseMsg<OrderDTO> countLatestConsume(@RequestParam("memberId") Long memberId) {
        return orderService.countLatestConsume(memberId);
    }

    /**
     * 根据会员号统计总消费
     * @param memberId
     * @return
     */
    @GetMapping(value = "/count-member-consume")
    @ApiOperation("根据会员号统计总消费")
    public ResponseMsg countMemberConsume(@RequestParam("memberId") Long memberId) {
        return orderService.countMemberConsume(memberId);
    }
}
