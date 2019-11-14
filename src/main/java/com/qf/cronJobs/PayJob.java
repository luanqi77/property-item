package com.qf.cronJobs;


import com.qf.service.DeductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/13 16:43
 */
@Slf4j
@Component
public class PayJob {
    @Autowired
    private DeductService deductService;
    public void pay(){
        deductService.payJob();
        log.info("执行扣费任务");
    }

}
