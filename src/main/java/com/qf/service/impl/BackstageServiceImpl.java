package com.qf.service.impl;

import com.qf.bean.UserAccount;
import com.qf.bean.UserRegisterRequest;
import com.qf.dao.*;
import com.qf.domain.Account;
import com.qf.domain.House;
import com.qf.domain.Staff;
import com.qf.domain.User;
import com.qf.service.BackstageService;
import com.qf.utils.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;

@Service
public class BackstageServiceImpl implements BackstageService {
    @Autowired
    private  Md5Utils md5Utils;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StaffMapper staffMapper;


    @Override
    public String login(Staff staff) {
        if (staff.getStaffNumber() != null && staff.getStaffNumber() != "" && staff.getPassword() != null && staff.getPassword() != "") {
            String staffNumber = staff.getStaffNumber();
            String password = staff.getPassword();
            //System.out.println(staff);
            try {
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(staffNumber, password);
                subject.login(token);
                if (subject.isAuthenticated()) {
                    return "success";
                } else {
                    return "fail";
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("fail");
                return "认证失败";
            }
        }else {
            return "账号或密码不能为空";
        }
    }

    @Override
    public String updateStaffPassword(Staff staff) {
        if (staff.getPassword()==null||staff.getPassword()==""){
            return "密码不能为空";
        }
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        String passwordCode = md5Utils.getPasswordCode(staff.getPassword(), principal);
        staff.setStaffNumber(principal);
        staff.setPassword(passwordCode);
        if (staffMapper.updatePassword(staff)>0){
            return "success";
        }
        return "fail";
    }

    @Override
    public String insertUser(UserRegisterRequest userRegisterRequest) {
        User user = userRegisterRequest.getUser();
        if (user.getRealname()==null||user.getRealname()==""||user.getTel()==""||user.getTel()==null){
            return "真实姓名或电话不能为空";
        }
        if (houseMapper.selectByUserId(userRegisterRequest.getHouseId())!=null){
            return "房屋已存在住户";
        }
        if (userMapper.insertSelective(userRegisterRequest.getUser())>0){
            Integer userId = userRegisterRequest.getUser().getUserId();
            //为house添加用户
            House house = new House();
            house.setHouseId(userRegisterRequest.getHouseId());
            house.setUserId(userId);
            houseMapper.updateByPrimaryKeySelective(house);
            //添加用户对应的账户表
            Account account = new Account();
            account.setUserId(userId);
            accountMapper.insertSelective(account);
            return "success";
        }
        return "fail";

    }

    @Override
    public int delUserById(User user) {
        Integer userId = user.getUserId();
        if (userId!=null) {
            houseMapper.removeMaster(userId);
            accountMapper.deleteByUserId(userId);
            return userMapper.deleteByPrimaryKey(userId);
        }
        return 0;
    }

    @Override
    public String updateUser(User user) {
        if (user.getTel()==null||user.getTel()==""||user.getRealname()!=null||user.getRealname()==""){
            return "真实姓名或电话不能为空";
        }
        userMapper.updateByPrimaryKeySelective(user);
        return "success";
    }

    @Override
    public List<UserAccount> findUserAccount() {
        return accountMapper.findUserAccount();
    }




}
