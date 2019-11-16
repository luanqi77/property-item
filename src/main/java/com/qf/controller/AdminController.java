package com.qf.controller;

import com.qf.aop.SystemControllerLog;
import com.qf.bean.PageBean;
import com.qf.bean.StaffAndRoleRequest;
import com.qf.domain.Deduct;
import com.qf.domain.Staff;
import com.qf.domain.StaffRole;
import com.qf.service.AdminService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {
    @Autowired
    private AdminService AdminService;


    //获得收费策略基本信息
    @RequestMapping("/getDeduct")
    @RequiresPermissions("payment_rule")
    public Deduct getDeduct(){
        Deduct deduct = AdminService.getDeduct();
        return deduct;
    }

    //更改收费策略基本信息
    @SystemControllerLog(methods = "更改收费策略")
    @RequestMapping("/updateDeduct")
    @RequiresPermissions("payment rule")
    public String updateDeduct(@RequestBody Deduct deduct){
        //System.out.println(deduct);
        return AdminService.updateDeduct(deduct);
    }
    //提醒缴费
    @SystemControllerLog(methods = "提醒缴费")
    @RequestMapping("/warnJob")
    @RequiresPermissions("payment_rule")
    public String warnJob(){
        return AdminService.warnJob();
    }

    //得到员工表
    @RequestMapping("/findStaff")
    @RequiresPermissions("staff_manage")
    public StaffAndRoleRequest findStaff(@RequestBody PageBean pageBean){
        return  AdminService.findStaff(pageBean);
    }


    //更改员工角色
    @SystemControllerLog(methods = "更改员工角色")
    @RequestMapping("/updateRole")
    @RequiresPermissions("staff_manage")
    public String updateRole(@RequestBody StaffRole staffRole){
        if (AdminService.updateRole(staffRole)>0){
            return "success";
        }
        return "fail";
    }

    //重置员工密码
    @SystemControllerLog(methods = "重置员工密码")
    @RequestMapping("/resetPassword")
    @RequiresPermissions("staff_manage")
    public String resetPassword(@RequestParam String staffNumber){
        if (AdminService.resetPassword(staffNumber)>0){
            return "success";
        }
        return "fail";
    }

    //新增员工√
    @SystemControllerLog(methods = "新增员工")
    @RequestMapping("/insertStaff")
    @RequiresPermissions("staff_manage")
    public String insertStaff(@RequestBody Staff staff){
        return AdminService.insertStaff(staff);
    }

    //移除员工
    @SystemControllerLog(methods = "移除员工")
    @RequestMapping("/delStaff")
    @RequiresPermissions("staff_manage")
    public String delStaff(@RequestBody Staff staff){
        if (AdminService.delStaff(staff)>1){
            return "success";
        }else {
            return "fail";
        }
    }

}
