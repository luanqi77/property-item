package com.qf.controller;

import com.qf.domain.Information;
import com.qf.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/12
 * @Time: 14:37
 */
@RestController
public class InformationController {
    @Autowired
    private InformationService informationService;

    @RequestMapping("/findAllInformation")
    public List<Information> findAllInformation(){
        return informationService.findAllInformation();
    }

    @RequestMapping("/insertInformation")
    public Integer insertInformation(@RequestBody Information information){
        System.out.println(information);
        Integer info = informationService.insertInformation(information);
        return info;
    }

    @RequestMapping("/deleteInformation")
    public void deleteInformation(@RequestBody Information information){

        informationService.deleteInformation(information.getInid());
        System.out.println(information.getInid());
    }

    @RequestMapping(value = "/updateInformation",method = RequestMethod.POST)
    public Integer updateInformation(@RequestBody Information information){
        Integer integer1 = informationService.updateInformation(information);
        return integer1;
    }

    @RequestMapping("/selectInformationById")
    public Information selectInformationById(@RequestBody Information information){
        Information inid = informationService.selectInformationById(information.getInid());
        System.out.println(inid);
        return inid;
    }

}
