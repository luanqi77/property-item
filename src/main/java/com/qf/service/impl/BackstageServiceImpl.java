package com.qf.service.impl;

import com.qf.bean.UserAccount;
import com.qf.bean.UserRegisterRequest;
import com.qf.dao.AccountMapper;
import com.qf.dao.HouseMapper;
import com.qf.dao.StaffMapper;
import com.qf.dao.UserMapper;
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

import java.util.List;

@Service
public class BackstageServiceImpl implements BackstageService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private Md5Utils md5Utils;
    @Override
    public String login(Staff staff) {
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
            return "fail";
        }
        return null;
    }

    @Override
    public String updateStaffPassword(Staff staff) {
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
    public int delUserById(int id) {
        houseMapper.removeMaster(id);
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<UserAccount> findUserAccount() {
        return accountMapper.findUserAccount();
    }

    @Override
    public int insertStaff(Staff staff) {
        return staffMapper.insertSelective(staff);
    }


}
