package com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin;

import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.OrderService;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.OrderProxy;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.OrderDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.model.ReverseOrderDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.OrderQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.ReverseOrderQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单远程调用服务实现类
 * 
 * @author litao
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderProxy orderProxy;

    /**
     * 查询会员退单记录
     * 
     * @param reverseCondition
     * @return
     */
    @Override
    public ResponseMsg<List<ReverseOrderDTO>> queryReverseListByCondition(ReverseOrderQuery reverseCondition) {
        reverseCondition.setTradeStatus((short) 90);
        return orderProxy.queryReverseListByCondition(reverseCondition);
    }

    /**
     * 查询会员订单记录
     * 
     * @param orderQuery
     * @return
     */
    @Override
    public ResponseMsg<List<OrderDTO>> queryOrderListByCondition(OrderQuery orderQuery) {
        orderQuery.setTradeStatus((short) 0);
        return orderProxy.queryOrderListByCondition(orderQuery);
    }

    /**
     * 查询会员最后一次消费记录
     * 
     * @param memberId
     * @return
     */
    @Override
    public ResponseMsg<OrderDTO> countLatestConsume(Long memberId) {
        return orderProxy.countLatestConsume(memberId);
    }

    /**
     * 根据会员号统计总消费
     * 
     * @param memberId
     * @return
     */
    @Override
    public ResponseMsg countMemberConsume(Long memberId) {
        return orderProxy.countMemberConsume(memberId);
    }
}
