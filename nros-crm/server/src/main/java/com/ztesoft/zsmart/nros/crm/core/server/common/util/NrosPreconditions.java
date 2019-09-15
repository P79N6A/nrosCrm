package com.ztesoft.zsmart.nros.crm.core.server.common.util;

import com.ztesoft.zsmart.nros.base.exception.ExceptionHandler;

import java.util.Collection;
import java.util.Map;

/**
 * 参数校验工具
 *
 * @author fan.chaolin
 * @date 2019/4/12
 */
public final class NrosPreconditions {

    private static NrosPreconditions instance = new NrosPreconditions();

    private NrosPreconditions() {
    }

    public static NrosPreconditions getInstance() {
        return instance;
    }

    public void notNull(Object object, String errorCode) {
        if (object == null) {
            throwException(errorCode);
        }
    }

    public void notEmpty(Collection<?> collection, String errorCode) {
        if (collection == null || collection.isEmpty()) {
            throwException(errorCode);
        }
    }

    public void notEmpty(Map<?, ?> map, String errorCode) {
        if (map == null || map.isEmpty()) {
            throwException(errorCode);
        }
    }

    public void hasText(String text, String errorCode) {
        if (text == null || text.isEmpty()) {
            throwException(errorCode);
        }
    }

    private static void throwException(String errorCode) {
        ExceptionHandler.publish(errorCode);
    }

}
