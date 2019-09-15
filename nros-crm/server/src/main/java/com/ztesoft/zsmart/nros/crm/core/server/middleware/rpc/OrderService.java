package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.OrderDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.ReverseOrderDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.OrderQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.ReverseOrderQuery;

import java.util.List;

/**
 * 订单服务远程调用接口定义
 * 
 * @author litao
 */
public interface OrderService {
    /**
     * 查询会员退单记录
     * 
     * @param reverseCondition
     * @return
     */
    ResponseMsg<List<ReverseOrderDTO>> queryReverseListByCondition(ReverseOrderQuery reverseCondition);

    /**
     * 查询会员订单记录
     * 
     * @param orderQuery
     * @return
     */
    ResponseMsg<List<OrderDTO>> queryOrderListByCondition(OrderQuery orderQuery);

    /**
     * 查询会员最后一次消费记录
     * 
     * @param memberId
     * @return
     */
    ResponseMsg<OrderDTO> countLatestConsume(Long memberId);

    /**
     * 根据会员号统计总消费
     * 
     * @param memberId
     * @return
     */
    ResponseMsg countMemberConsume(Long memberId);
}
