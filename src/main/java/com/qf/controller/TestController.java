package com.qf.controller;

import com.qf.aop.SystemControllerLog;
import com.qf.bean.PageBean;
import com.qf.bean.UserAccount;
import com.qf.bean.UserAccountResponse;
import com.qf.domain.Deduct;
import com.qf.domain.Staff;
import com.qf.service.AdminService;
import com.qf.service.BackstageService;
import com.qf.service.UserSolrService;
import com.qf.utils.Md5Utils;
import com.qf.utils.SchedulerUtils;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class TestController {
    @Autowired
    private SchedulerUtils schedulerUtils;
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private AdminService deductService;
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
       return null;

    }
    @RequestMapping("/test5")
    public String test5(){
        return deductService.payJob();
    }
    //动态停止定时任务
    @RequestMapping("/test6")
    public String test6(){
        JobKey key = new JobKey("payJob");
        try {
            scheduler.pauseJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "success";
    }
    //动态启动定时任务
    @RequestMapping("/test7")
    public String start() throws Exception {

        JobKey key = new JobKey("payJob");
        scheduler.resumeJob(key);
        return "start";
    }
    //动态修改定时任务执行策略
    @RequestMapping("/test8")
    public String trigger() throws Exception {
        // 获取任务
        JobKey jobKey = new JobKey("payJob");
        // 获取 jobDetail
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        // 生成 trigger
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                .build();
        // 删除任务，不删除会报错。报任务已存在
        scheduler.deleteJob(jobKey);
        // 启动任务
        scheduler.scheduleJob(jobDetail, trigger);
        return "trigger";
    }
    @RequestMapping("/payTest")
    @SystemControllerLog(methods = "测试" ,serviceClass = "测试类" )
    public String payTest(@RequestBody Staff staff,String args) {
            //schedulerUtils.updateExecuteTime("payJob","0/"+second+" * * * * ? *");
            //schedulerUtils.updateExecuteTime("warnJob","0/"+second+" * * * * ? *");
            log.info("方法执行！！！");
        return null;
    }

}
