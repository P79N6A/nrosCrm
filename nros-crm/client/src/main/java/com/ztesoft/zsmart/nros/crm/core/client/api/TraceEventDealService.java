package com.ztesoft.zsmart.nros.crm.core.client.api;

/**
 * 处理轨迹中的事件interface
 *
 * @author litao
 * @date 2019/6/12
 */
public interface TraceEventDealService {
    /**
     * 处理方法
     * 
     * @param jsonParm JSON格式的字符串参数
     * @author PQ
     * @date 2019/6/17
     */
    void doWork(String jsonParm);
}
