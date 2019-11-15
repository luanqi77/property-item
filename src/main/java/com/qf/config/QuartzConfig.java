package com.qf.config;

import com.qf.cronJobs.PayJob;
import com.qf.cronJobs.warnJob;
import com.qf.dao.DeductMapper;
import com.qf.domain.Deduct;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/14 10:56
 */
@Configuration
public class QuartzConfig {
    @Autowired
    private DeductMapper deductMapper;
    // 任务内容
    @Bean
    public MethodInvokingJobDetailFactoryBean payJob() {
<<<<<<< HEAD


=======
>>>>>>> 9798d5f7af5550d0b3bb4bed2bb64de6c5bff7a2
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        factoryBean.setConcurrent(true);
        // 使用哪个对象
        factoryBean.setTargetObject(new PayJob());
        // 使用哪个方法
        factoryBean.setTargetMethod("pay");

        return factoryBean;
    }
    // 定义什么时候做，使用 cron 表达式
    @Bean
    public CronTriggerFactoryBean payCron(@Qualifier("payJob") MethodInvokingJobDetailFactoryBean payJob){
        Deduct deduct = deductMapper.getDeduct();
        Integer deductTime = deduct.getDeductTime();
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        // 设置job对象
        factoryBean.setJobDetail(payJob.getObject());
        // 设置执行时间
        String cronString="0 0 0 "+deductTime+" * ? *";
        System.out.println(cronString);
        factoryBean.setCronExpression(cronString);
        return  factoryBean;
    }

    // 任务内容
    @Bean
    public MethodInvokingJobDetailFactoryBean warnJob() {
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        factoryBean.setConcurrent(true);
        // 使用哪个对象
        factoryBean.setTargetObject(new warnJob());
        // 使用哪个方法
        factoryBean.setTargetMethod("warn");
        return factoryBean;
    }

    // 定义什么时候做，使用 cron 表达式
    @Bean
    public CronTriggerFactoryBean warnCron(@Qualifier("warnJob") MethodInvokingJobDetailFactoryBean warnJob){
        Deduct deduct = deductMapper.getDeduct();
        System.out.println(deduct);
        Integer warnTime = deduct.getWarnTime();
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        // 设置job对象
        factoryBean.setJobDetail(warnJob.getObject());
        // 设置执行时间
        String cronString="0 0 0 "+warnTime+" * ? *";
        System.out.println(cronString);
        factoryBean.setCronExpression(cronString);
        return  factoryBean;
    }

    // 定义 任务，传入 triggers
    @Bean
    public SchedulerFactoryBean scheduler(Trigger... triggers){
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        // 设置 triggers
        factoryBean.setTriggers(triggers);
        // 自动运行
        factoryBean.setAutoStartup(true);
        return factoryBean;
    }



}
