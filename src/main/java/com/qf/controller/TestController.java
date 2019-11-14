package com.qf.controller;

import com.qf.bean.PageBean;
import com.qf.bean.UserAccount;
import com.qf.bean.UserAccountResponse;
import com.qf.service.BackstageService;
import com.qf.service.DeductService;
import com.qf.service.UserSolrService;
import com.qf.utils.Md5Utils;
import com.qf.utils.SchedulerUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class TestController {
    @Autowired
    private SchedulerUtils schedulerUtils;
    @Autowired
    private Scheduler scheduler;
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
    public String payTest(@RequestParam("second")Integer second){
        try {
            schedulerUtils.updateExecuteTime("payJob","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
