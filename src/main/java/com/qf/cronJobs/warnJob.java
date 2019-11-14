package com.qf.cronJobs;

import com.qf.service.DeductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/13 16:44
 */
@Slf4j
@Configuration
public class warnJob {
    @Autowired
    private DeductService deductService;
    public void warn(){
        deductService.warnJob();
        log.info("执行提醒任务");
    }



}
