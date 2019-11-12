package com.qf.controller;

import com.qf.domain.Staff;
import com.qf.service.BackstageService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackstageController {
    @Autowired
    private BackstageService backstageService;


    @RequestMapping("/login")
    public String login(@RequestBody Staff staff){
            return backstageService.login(staff);
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
        return  backstageService.updatePassword(staff);
    }

}
