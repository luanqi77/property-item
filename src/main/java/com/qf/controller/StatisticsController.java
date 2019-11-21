package com.qf.controller;

import com.qf.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StatisticsController {
    @Autowired
    private HouseService houseService;
    /*
    * 物业缴费率*/
    @RequestMapping("/getContributionRate")
    public  List<String> getContributionRate(){
        List<String> list = new ArrayList<>();
        String format="";
        for (int i = 1; i < 6; i++) {
            Integer noArrears = houseService.selectNoArrears(i);
            Integer household = houseService.selectHousehold(i);
            Format decimalFormat = new DecimalFormat("0.00");
            format = decimalFormat.format((double) noArrears / household);
            list.add(format);
            System.out.println(format);
        }
        return list;
    }

}
