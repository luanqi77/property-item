package com.qf.controller;

import com.qf.bean.PageBean;
import com.qf.bean.UserAccount;
import com.qf.bean.UserAccountResponse;
import com.qf.service.BackstageService;
import com.qf.service.DeductService;
import com.qf.service.UserSolrService;
import com.qf.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class TestController {
    @Autowired
    private DeductService deductService;
    @Autowired
    private Md5Utils md5Utils;
    @Autowired
    private UserSolrService userSolrService;
    @Autowired
    private BackstageService backstageService;
    @RequestMapping("/test")
    public List<UserAccount> test(){
        return backstageService.findUserAccount();
    }

    @RequestMapping("/test2")
    public String test2(){
        return userSolrService.dataIntoSolrFromDb();
    }
    @RequestMapping("/test3")
    public UserAccountResponse test3(@RequestBody PageBean pageBean){

        return userSolrService.queryUserAccountsByPage(pageBean);
    }
    @RequestMapping("/test4")
    public List test4(){
       return deductService.warnJob();

    }
}
