package com.qf.controller;

import com.qf.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.Format;

@RestController
public class StatisticsController {
    @Autowired
    private HouseService houseService;
    /*
    * 物业缴费率*/
    @RequestMapping("/getContributionRate")
    public String getContributionRate(@RequestParam("build") Integer build){

        Integer noArrears = houseService.selectNoArrears(build);
        Integer household = houseService.selectHousehold(build);
        Format decimalFormat = new DecimalFormat("0.00");
        String format = decimalFormat.format((double) noArrears / household);
        System.out.println(format);

        return format;
    }

}
