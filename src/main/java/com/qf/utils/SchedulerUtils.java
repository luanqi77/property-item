package com.qf.utils;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/14 15:00
 */
@Component
@Slf4j
public class SchedulerUtils {
    @Autowired
    private Scheduler scheduler;


    //动态停止定时任务
    public String stop(String jobKey){
        JobKey key = new JobKey(jobKey);
        try {
            scheduler.pauseJob(key);
            log.info("停止定时任务"+jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "stop";
    }

    //动态启动定时任务
    public String start(String jobKey) throws Exception {

        JobKey key = new JobKey(jobKey);
        scheduler.resumeJob(key);
        log.info("启动定时任务"+jobKey);
        return "start";
    }

    //动态修改定时任务执行策略
    public String updateExecuteTime(String jobKey,String cronString) throws Exception {
        // 获取任务
        JobKey key = new JobKey(jobKey);
        // 获取 jobDetail
        JobDetail jobDetail = scheduler.getJobDetail(key);
        // 生成 trigger
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(cronString))
                .build();
        // 删除任务，不删除会报错。报任务已存在
        scheduler.deleteJob(key);
        // 启动任务
        scheduler.scheduleJob(jobDetail, trigger);
        log.info("更新定时任务策略"+jobKey);
        return "trigger";
    }
}
