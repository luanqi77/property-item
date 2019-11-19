package com.qf.cronJobs;


import com.qf.service.AdminService;
import com.qf.service.impl.AdminServiceImpl;
import com.qf.utils.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;


/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/13 16:43
 */
@Slf4j
@Configuration
public class PayJob {
    public void pay(){
        AdminService deductService = (AdminServiceImpl)ApplicationContextUtil.getBean("deductService");
        deductService.payJob();
        log.info("执行扣费任务");
    }

}
