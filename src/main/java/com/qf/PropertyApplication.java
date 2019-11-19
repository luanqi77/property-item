package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan("com.qf")
public class PropertyApplication {
    public static void main(String[] args) {
        SpringApplication.run(PropertyApplication.class,args);
    }
}
