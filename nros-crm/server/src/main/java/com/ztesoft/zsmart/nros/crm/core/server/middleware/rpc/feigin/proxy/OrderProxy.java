package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.OrderDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.OrderQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.ReverseOrderQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "order")
public interface OrderProxy {

    /**
     * 查询会员退单记录
     * 
     * @param reverseCondition
     * @return
     */
    @PostMapping(value = "/nrosapi/order/v1/reverse/search")
    ResponseMsg queryReverseListByCondition(@RequestBody ReverseOrderQuery reverseCondition);

    /**
     * 查询会员订单记录
     * 
     * @param orderQuery
     * @return
     */
    @PostMapping(value = "/nrosapi/order/v1/order/get-order-list")
    ResponseMsg queryOrderListByCondition(@RequestBody OrderQuery orderQuery);

    /**
     * 查询会员最后一次消费记录
     * 
     * @param memberId
     * @return
     */
    @PostMapping(value = "/nrosapi/order/v1/order/count-latest-consume")
    ResponseMsg<OrderDTO> countLatestConsume(@RequestParam("memberId") Long memberId);

    /**
     * 根据会员号统计总消费
     * 
     * @param memberId
     * @return
     */
    @PostMapping(value = "/nrosapi/order/v1/order/count-member-consume")
    ResponseMsg countMemberConsume(@RequestParam("memberId") Long memberId);
}
