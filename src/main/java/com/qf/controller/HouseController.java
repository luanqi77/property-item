package com.qf.controller;

import com.qf.domain.House;
import com.qf.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Company: 憨憨互联
 * @Author: 张朝
 * @Date: 2019/11/18
 * @Time: 16:20
 */
@RestController
public class HouseController {
     @Autowired
     private HouseService houseService;

     @RequestMapping("/findHouseById")
     public House findHouseById(@RequestBody House userId){
         if (houseService.selectByUserId(userId.getUserId())==null){
             return null;
         }else {
             return houseService.selectByUserId(userId.getUserId());
         }
     }
}
