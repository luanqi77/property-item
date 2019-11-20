package com.qf.service.impl;

import com.qf.bean.PageBean;
import com.qf.bean.StaffAndRole;
import com.qf.bean.StaffAndRoleRequest;
import com.qf.bean.logInfoResponse;
import com.qf.dao.*;
import com.qf.domain.*;
import com.qf.service.AdminService;
import com.qf.utils.Md5Utils;
import com.qf.utils.SchedulerUtils;
import com.qf.utils.TelMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service("deductService")
public class AdminServiceImpl implements AdminService {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private LogInfoMapper logInfoMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private Md5Utils md5Utils;
    @Autowired
    private TelMessageUtils telMessageUtils;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private DeductMapper deductMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private SchedulerUtils schedulerUtils;
    @Autowired
    private StaffRoleMapper staffRoleMapper;

    @Override
    public Deduct getDeduct() {
        Deduct deduct = deductMapper.getDeduct();
        return deduct;
    }

    @Override
    public String updateDeduct(Deduct deduct) {
        if (deduct.getHotFee() != null && deduct.getPropertyFee() != null && deduct.getWarnTime() != null && deduct.getDeductTime() != null) {
            if (deductMapper.updateDeduct(deduct) > 0) {
                houseMapper.updateFee(deduct);
                try {
                    Integer deductTime = deduct.getDeductTime();
                    Integer warnTime = deduct.getWarnTime();
                    String cronString = "0 0 0 " + deductTime + " * ? *";
                    String cronString2 = "0 0 0 " + warnTime + " * ? *";
                    schedulerUtils.updateExecuteTime("payJob", cronString);
                    schedulerUtils.updateExecuteTime("warnJob", cronString2);
                    return "success";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "error";
                }
            }
            return "未更改";

        }
        return "值不能为空";

    }

    @Override
    public String warnJob() {
        List<User> users = userMapper.findUserToPay();
        return telMessageUtils.warnMessage(users);
    }

    @Override
    public String payJob() {
        if (accountMapper.payFee()>0){
            System.out.println("本月费用已扣除");
            return "success";
        }
        return null;
    }

    @Override
    public StaffAndRoleRequest findStaff(PageBean pageBean) {
        if (pageBean.getCurrentPage()==null||pageBean.getPageSize()==null){
            return null;
        }
        int startIndex = (pageBean.getCurrentPage() - 1) * pageBean.getPageSize();
        pageBean.setStartIndex(startIndex);
        int endIndex = startIndex + pageBean.getPageSize();
        pageBean.setEndIndex(endIndex);

        List<StaffAndRole> staffs = staffRoleMapper.findStaff(pageBean);

        StaffAndRoleRequest staffAndRoleRequest = new StaffAndRoleRequest();
        staffAndRoleRequest.setStaffs(staffs);
        staffAndRoleRequest.setTotal(staffRoleMapper.findStaffCount(pageBean));
        return staffAndRoleRequest;
    }

    @Override
    public Integer updateRole(StaffRole staffRole) {
        if (staffRole.getStaffId()==null||staffRole.getRoleId()==null){
            return 0;
        }
        return staffRoleMapper.updateRole(staffRole);
    }

    @Override
    public Integer resetPassword(String staffNumber) {
        if (staffNumber==null){
            return 0;
        }
        Staff staff = new Staff();
        String passwordCode = md5Utils.getPasswordCode("123456", staffNumber);
        staff.setPassword(passwordCode);
        staff.setStaffNumber(staffNumber);
        return staffMapper.updatePassword(staff);
    }

    @Override
    public String insertStaff(Staff staff, int roleId) {
        if (staff.getStaffNumber()==null||staff.getStaffNumber()==""||staff.getStaffName()==null||staff.getStaffName()==""){
            return "工号或姓名不能为空";
        }
        String staffNumber = staff.getStaffNumber();
        if (staffMapper.selectStaffByStaffNumber(staffNumber)!=null){
            return "工号已存在";
        }
        String passwordCode = md5Utils.getPasswordCode("123456", staff.getStaffNumber());
        staff.setPassword(passwordCode);
        if (staffMapper.insertSelective(staff)>0){
            StaffRole staffRole = new StaffRole();
            staffRole.setRoleId(roleId);
            staffRole.setStaffId(staff.getStaffId());
            staffRoleMapper.insert(staffRole);
            return "success";
        }
        return "fail";
    }

    @Override
    public Integer delStaff(Staff staff) {
        if (staff.getStaffId()==null){
            return 0;
        }
       return staffMapper.deleteByPrimaryKey(staff.getStaffId());

    }

    @Override
    public logInfoResponse findLogInfo(PageBean pageBean) {
        if (pageBean.getCurrentPage()!=null&&pageBean.getPageSize()!=null){
            pageBean.setStartIndex((pageBean.getCurrentPage()-1)*pageBean.getPageSize());
            pageBean.setEndIndex(pageBean.getCurrentPage()*pageBean.getPageSize());
            if (pageBean.getLogDate()!=null){
                String logDate = SIMPLE_DATE_FORMAT.format(pageBean.getLogDate());
                pageBean.setKeywords(logDate);
            }
            List<LogInfo> logInfos = logInfoMapper.findLogInfo(pageBean);
            logInfoResponse logInfoResponse = new logInfoResponse();
            logInfoResponse.setLogInfos(logInfos);
            logInfoResponse.setTotal(logInfoMapper.getLogInfoCount(pageBean));
            return logInfoResponse;
        }
        return null;
    }

    @Override
    public StaffAndRole getStaffInfo(int id) {
        return staffRoleMapper.getStaffInfo(id);
    }


}
