package com.qf.controller;

import com.qf.bean.PageBean;
import com.qf.bean.UserAccountResponse;
import com.qf.bean.UserRegisterRequest;
import com.qf.domain.Staff;
import com.qf.domain.User;
import com.qf.service.BackstageService;
import com.qf.service.UserService;
import com.qf.service.UserSolrService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackstageController {
    @Autowired
    private UserSolrService userSolrService;
    @Autowired
    private BackstageService backstageService;
    //员工的登录和注销
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
    //员工修改密码
    @RequestMapping("/updateStaffPassword")
    public String updatePassword(@RequestBody Staff staff){
        return  backstageService.updateStaffPassword(staff);
    }




    //后台对用户的增删改查
    @RequestMapping("/insertUser")
    public String insertUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        String results = backstageService.insertUser(userRegisterRequest);
        //更新solr索引库
        userSolrService.dataIntoSolrFromDb();
        return results;
    }
    @RequestMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        if (backstageService.updateUser(user)>0){
            //更新solr索引库
            userSolrService.dataIntoSolrFromDb();
            return "success";
        }
        return "fail";
    }
    @RequestMapping("/removeMaster")
    public String delUser(@RequestBody User user){
        if (backstageService.delUserById(user.getUserId())>0){
            //更新solr索引库
            userSolrService.dataIntoSolrFromDb();
            return "success";
        }
        return "fail";
    }
    @RequestMapping("/findUserAccount")
    public UserAccountResponse getUserAccount(@RequestBody PageBean pageBean){
        return userSolrService.queryUserAccountsByPage(pageBean);
    }





}
