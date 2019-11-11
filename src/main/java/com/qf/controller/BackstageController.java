package com.qf.controller;

import com.qf.dao.StaffMapper;
import com.qf.domain.Staff;
import com.qf.utils.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackstageController {
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private Md5Utils md5Utils;

    @RequestMapping("/login")
    public String login(@RequestBody Staff staff){
        String staffNumber = staff.getStaffNumber();
        String password = staff.getPassword();
        if(staffNumber!=""&&staffNumber!=null&&password!=""&&password!=null) {
            //System.out.println(staff);
            try {
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(staffNumber, password);
                subject.login(token);
                if (subject.isAuthenticated()) {
                    Staff byStaffNumber = staffMapper.findByStaffNumber(staffNumber);
                    return byStaffNumber.getStaffName();
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("登录失败");
            }
        }else {
            return "值不能为空";
        }
        return null;
    }

    @RequestMapping("/logout")
    public String logout(){
        try {
            SecurityUtils.getSubject().logout();
            return "登出成功";
        }catch (Exception e){
            e.printStackTrace();
            return "登出失败";
        }
    }
    @RequestMapping("/updatePassword")
    public String updatePassword(@RequestBody Staff staff){
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        String passwordCode = md5Utils.getPasswordCode(staff.getPassword(), principal);
        staff.setStaffNumber(principal);
        staff.setPassword(passwordCode);
        if (staffMapper.updatePassword(staff)>0){
            return "修改成功";
        }
        return "修改失败";
    }


}
