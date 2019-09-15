/**
 * [Product]
 * nrospromotion
 * [Copyright]
 * Copyright © 2019 ZTESoft All Rights Reserved.
 * [FileName]
 * TargetUserDTO.java
 * [History]
 * Version  Date      Author     Content
 * -------- --------- ---------- ------------------------
 * 1.0.0    2019/7/19   PQ         最初版本
 */
package com.ztesoft.zsmart.nros.crm.core.server.middleware.engine.activiti.model;

import java.io.Serializable;

import lombok.Data;

/**
 * activiti中目标用户DTO
 * 
 * @author PQ
 * @date 2019/7/19
 */
@Data
public class TargetUserDTO implements Serializable {

    /**
     * TargetUserDTO-id
     */
    private Long id;

    /**
     * TargetUserDTO-phone
     */
    private String phone;

    /**
     * TargetUserDTO-name
     */
    private String name;

    /**
     * TargetUserDTO-identity
     */
    private String identity;

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + (id == null ? 0 : id.hashCode());
        return result;
    }

    // 重写equals方法，只要ID相等，即认为是同一目标用户
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            TargetUserDTO targetUserDTO = (TargetUserDTO) obj;
            if (null == id) {
                return targetUserDTO.getId() == null;
            }
            else {
                return id.equals(targetUserDTO.getId());
            }
        }
        return false;
    }
}
