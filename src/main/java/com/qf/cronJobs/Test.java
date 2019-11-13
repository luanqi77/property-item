package com.qf.cronJobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/13 16:40
 */
@Component
public class Test {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd");

    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime() {
        System.out.println("今天" + dateFormat.format(new Date())+"号");
    }

}
