package com.qf.controller;

import com.qf.domain.UserParking;
import com.qf.response.ResponseUser;
import com.qf.service.ParkingSpacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/19
 * @Time: 14:51
 */
@RestController
public class ParkingSpacesController {
    @Autowired
    private ParkingSpacesService parkingSpacesService;

    @RequestMapping("/findAllParkingSpaces/{page}/{size}")
    public ResponseUser findAllParkingSpaces(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        ResponseUser list1 = parkingSpacesService.findAllParkingSpaces(page, size);
        return list1;
    }

    @RequestMapping("/selectParkingSpacesById")
    public UserParking selectParkingSpacesById(@RequestBody UserParking userParking){
        UserParking userParking1 = parkingSpacesService.selectParkingSpacesById(userParking.getUp_id());
        System.out.println(userParking1);
        return userParking1;
    }

    @RequestMapping(value = "/updateParkingSpaces",method = RequestMethod.POST)
    public Integer updateParkingSpaces(@RequestBody UserParking userParking){
        Integer parking2 = parkingSpacesService.updateParkingSpaces(userParking);
        return parking2;
    }
}
