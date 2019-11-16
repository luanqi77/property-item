package com.qf.service;

import com.qf.bean.PageBean;
import com.qf.bean.StaffAndRoleRequest;
import com.qf.bean.logInfoResponse;
import com.qf.domain.Deduct;
import com.qf.domain.Staff;
import com.qf.domain.StaffRole;


public interface AdminService {
    Deduct getDeduct();
    String updateDeduct(Deduct deduct);
    String warnJob();
    String payJob();

    StaffAndRoleRequest findStaff(PageBean pageBean);

    Integer updateRole(StaffRole staffRole);

    Integer resetPassword(String staffNumber);

    String insertStaff(Staff staff);

    Integer delStaff(Staff staff);

    logInfoResponse findLogInfo(PageBean pageBean);
}
