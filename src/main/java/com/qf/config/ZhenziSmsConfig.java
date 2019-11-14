package com.qf.config;

import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/13 20:21
 */
@Configuration
public class ZhenziSmsConfig {
    @Bean
    public ZhenziSmsClient getClient(){
        return new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "103150", "092e748f-80cf-4aab-98b6-67e91ce7742c");
    }
}
