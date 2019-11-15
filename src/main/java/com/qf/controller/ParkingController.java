package com.qf.controller;

import com.qf.domain.Parking;
import com.qf.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/15
 * @Time: 11:55
 */
@RestController
public class ParkingController {
    @Resource
    private ParkingService parkingService;

    @RequestMapping("/selectParkingById/{userId}")
    public List<Parking> selectParkingById(@PathVariable("userId") Integer userId){
        return parkingService.selectParkingById(userId);

    }

}
