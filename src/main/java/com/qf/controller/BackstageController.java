package com.qf.controller;

import com.qf.aop.SystemControllerLog;
import com.qf.bean.PageBean;
import com.qf.bean.UserAccountResponse;
import com.qf.bean.UserRegisterRequest;
import com.qf.domain.Staff;
import com.qf.domain.User;
import com.qf.service.BackstageService;
import com.qf.service.UserSolrService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BackstageController{
    @Autowired
    private UserSolrService userSolrService;
    @Autowired
    private BackstageService backstageService;

    //员工的登录和注销√
    @RequestMapping("/StaffLogin")
    public String staffLogin(@RequestBody Staff staff){
            return backstageService.login(staff);
    }
    @RequestMapping("/StaffLogout")
    public String StaffLogout(){
        if (SecurityUtils.getSubject().getPrincipal()!=null){
            SecurityUtils.getSubject().logout();
            return "success";
        }
        return "fail";
    }

    //员工修改密码√
    @RequestMapping("/updateStaffPassword")
    @RequiresAuthentication
    public String updatePassword(@RequestBody Staff staff){
        return  backstageService.updateStaffPassword(staff);
    }



    //后台对用户的增删改查

    //新增用户√
    @RequiresPermissions("user_account")
    @SystemControllerLog(methods = "新增用户")
    @RequestMapping("/insertUser")
    public String insertUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        String results = backstageService.insertUser(userRegisterRequest);
        //更新solr索引库
        userSolrService.dataIntoSolrFromDb();
        return results;
    }

    //更改用户信息√
    @SystemControllerLog(methods = "更改用户信息")
    @RequestMapping("/updateUser")
    @RequiresPermissions("user_account")
    public String updateUser(@RequestBody User user){
        String result = backstageService.updateUser(user);
        userSolrService.dataIntoSolrFromDb();
        return result;
    }

    //移除用户√
    @SystemControllerLog(methods = "移除用户")
    @RequestMapping("/removeMaster")
    @RequiresPermissions("user_account")
    public String delUser(@RequestBody User user){
        if (backstageService.delUserById(user)>0){
            //更新solr索引库
            userSolrService.dataIntoSolrFromDb();
            return "success";
        }
        return "fail";
    }

    //得到用户账户表√
    @RequestMapping("/findUserAccount")
    @RequiresPermissions("user_account")
    public UserAccountResponse getUserAccount(@RequestBody PageBean pageBean){
        return userSolrService.queryUserAccountsByPage(pageBean);
    }

    //获取当前用户信息
    @RequestMapping("/getCurrentStaff")
    public Staff getCurrentStaff(){
        return backstageService.getCurrentStaff();
    }

    //根据用户id获得用户信息
    @RequestMapping("/getUserById")
    public User getUserById(@RequestParam int id){
        return backstageService.getUserById(id);
    }








}
