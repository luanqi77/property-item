package com.qf.bean;

import lombok.Data;

import java.util.List;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/15 16:27
 */
@Data
public class StaffAndRoleRequest {
    private List<StaffAndRole> staffs;
    private Long total;
}
