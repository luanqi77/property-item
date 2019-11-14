package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PropertyApplication {
    public static void main(String[] args) {
        SpringApplication.run(PropertyApplication.class,args);
    }
}
