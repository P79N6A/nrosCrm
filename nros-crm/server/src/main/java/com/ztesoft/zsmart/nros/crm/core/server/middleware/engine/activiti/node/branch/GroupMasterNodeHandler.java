package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.branch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.enums.MarketingNodeExecuteTypeEnum;
import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Maps;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingGroupWayEnum;
import com.ztesoft.zsmart.nros.crm.core.server.common.enums.MarketingTypeEnum;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model.TargetUserDTO;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.node.BaseFlowNodeHandler;

/**
 * 分组信息放在param参数中
 *
 * @author admin
 * @date 2018/6/6
 **/
@Component
public class GroupMasterNodeHandler extends BaseFlowNodeHandler {
    // public class GroupMasterFlowNode implements ExecutionListener {

    private transient Logger logger = LoggerFactory.getLogger(GroupMasterNodeHandler.class);

    /**
     * 如果是主动营销,将筛选列表打乱
     *
     * @param delegateExecution
     * @return
     */
    @Override
    public void doBusiness(DelegateExecution delegateExecution, String innerParam) {
        String marketingType = (String) delegateExecution.getVariable("marketingType");
        List<TargetUserDTO> targetUserListYes = (List<TargetUserDTO>) delegateExecution
            .getVariable("targetUserListYes");
        // String innerParam = (String) param.getValue(delegateExecution);
        JSONObject groupParam = JSONObject.parseObject(innerParam);
        Integer groupWay = groupParam.getInteger("groupWay");
        JSONArray groupMasterArrayParam = groupParam.getJSONArray("groupMasterParam");
        // 目标用户列表按分组放置的索引
        List<int[]> userIndexes = null;

        if (groupWay == null || groupWay == MarketingGroupWayEnum.RANDOM_GROUP.ordinal()) {
            // 如果目标用户列表为空,不继续往下执行
            if (CollectionUtils.isEmpty(targetUserListYes)) {
                logger.info("GroupMasterFlowNode 上下文中没有目标客群");
            }
            else {
                // 营销类型（1：主动营销；2事件营销）
                if (MarketingTypeEnum.PUSH.getCode().equals(marketingType)) {
                    // 打乱目标用户列表
                    Collections.shuffle(targetUserListYes);
                    // 将目标用户列表的数据进行分组
                    userIndexes = splitUsers(targetUserListYes.size(), groupMasterArrayParam);
                }
                else {
                    userIndexes = weightAllot(groupMasterArrayParam);
                }
            }

        }
        /*
         * else { // 属性分组不用做任何事,筛选交给分组分支节点完成 }
         */
        Map<String, Object> groupMasterParams = Maps.newHashMap();
        groupMasterParams.put("targetUserListYes", targetUserListYes);
        groupMasterParams.put("userIndexes", userIndexes);
        delegateExecution.setVariable("groupMasterParams_" + delegateExecution.getCurrentActivityId(),
            groupMasterParams);
    }

    /**
     * 拆分用户列表
     *
     * @return
     */
    public List<int[]> splitUsers(int userListSize, JSONArray jsonArray) {
        List<Integer> rates = new ArrayList<>(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            Integer rate = jsonArray.getJSONObject(i).getInteger("rate");
            rates.add(rate);
        }
        List<Integer> integerList = this.pickUpAndOverPlus(userListSize, rates);

        List<int[]> userIndexes = new ArrayList<>();
        int[] indexes;
        int index = 0;
        for (Integer count : integerList) {
            indexes = new int[count];
            for (int i = 0; i < count; i++) {
                indexes[i] = index + i;
            }
            userIndexes.add(indexes);
            index += count;
        }
        return userIndexes;

    }

    /**
     * 权重分配,被分配的分支节点才有数据,其他都是Null
     *
     * @return
     */
    private List<int[]> weightAllot(JSONArray jsonArray) {
        List<int[]> userIndexes = new ArrayList<>();
        int rate = 0;
        int random = RandomUtils.nextInt(0, 100);
        for (int i = 0; i < jsonArray.size(); i++) {
            if (rate <= random && random < jsonArray.getJSONObject(i).getInteger("rate") + rate) {
                int[] weight = new int[1];
                weight[0] = 0;
                userIndexes.add(weight);
                rate += jsonArray.getJSONObject(i).getInteger("rate");
            }
            else {
                userIndexes.add(null);
                rate += jsonArray.getJSONObject(i).getInteger("rate");
            }
        }
        return userIndexes;
    }

    /**
     * 前n-1个取整(小数位直接抹掉),第n个剩余所有
     *
     * @param sum 需要拆分的List的总数
     * @param rateList 百分比
     * @return
     */
    private List<Integer> pickUpAndOverPlus(int sum, List<Integer> rateList) {
        List<Integer> result = new ArrayList<>(rateList.size());
        int temp = 0;
        for (int i = 0; i < rateList.size(); i++) {
            if (i < rateList.size() - 1) {
                int count = rateList.get(i) * sum / 100;
                result.add(count);
                temp += count;
            }
            else {
                result.add(sum - temp);
            }
        }
        return result;
    }

    @Override
    public String getNodeType() {
        return MarketingNodeExecuteTypeEnum.GROUP_MASTER.getCode();
    }
}
