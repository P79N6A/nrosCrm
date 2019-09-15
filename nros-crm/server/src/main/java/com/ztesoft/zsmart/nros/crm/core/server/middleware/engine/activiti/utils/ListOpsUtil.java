/**
 * [Product]
 * nrospromotion
 * [Copyright]
 * Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 * ListOpsUtil.java
 * [History]
 * Version  Date      Author     Content
 * -------- --------- ---------- ------------------------
 * 1.0.0    2019/6/17   PQ         最初版本
 */
package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

public class ListOpsUtil {
    /**
     * 取两个List集合的并集
     * 
     * @param targetList
     * @param otherList
     * @return java.util.List<T>
     * @author PQ
     * @date 2019/6/17
     */
    public static <T> List<T> add(List<T> targetList, List<T> otherList) {
        if (CollectionUtils.isEmpty(targetList) || CollectionUtils.isEmpty(otherList)) {
            return null;
        }
        return targetList.parallelStream().filter(targetUser -> otherList.contains(targetUser))
            .collect(Collectors.toList());
    }

    /**
     * 取targetList和otherList的差集（即：在targetList中且不在otherList中的元素集合）
     * 
     * @param targetList
     * @param otherList
     * @return java.util.List<T>
     * @author PQ
     * @date 2019/6/17
     */
    public static <T> List<T> not(List<T> targetList, List<T> otherList) {
        if (CollectionUtils.isEmpty(targetList) || CollectionUtils.isEmpty(otherList)) {
            return targetList;
        }
        else {
            return targetList.parallelStream().filter(targetUser -> !otherList.contains(targetUser))
                .collect(Collectors.toList());
        }
    }

}
