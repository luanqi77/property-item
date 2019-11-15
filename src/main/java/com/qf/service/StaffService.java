package com.qf.service;

import com.qf.domain.Staff;

/**
 * @PackageName:com.qf.service;
 * @ClassName:StaffService;
 * @author:马浩雲
 * @date2019/11/1416:36
 */
public interface StaffService {

    Staff selectStaffByStaffNumber(String staffNumber);

}
