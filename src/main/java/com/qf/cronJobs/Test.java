package com.qf.cronJobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/13 16:40
 */
//@Component
@Slf4j
//@Async
@Configuration
public class Test {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd");

    //@Scheduled(fixedRate = 6000)
    public void reportCurrentTime() {
        System.out.println("今天" + dateFormat.format(new Date())+"号");
    }
    //@Scheduled(fixedRate = 6000)
    public void reportCurrentTime2() {
        System.out.println("今天" + dateFormat.format(new Date())+"号");
    }

}
