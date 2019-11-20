package com.qf.controller;

import com.qf.domain.Information;
import com.qf.response.ResponseUser;
import com.qf.service.InformationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/findAllInformationBypage/{page}/{size}")
    public ResponseUser findAllInformation(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        ResponseUser  list =  informationService.findAllInformationBypage(page,size);
        System.out.println(list);
        return list;

    }
    @RequestMapping("/findAllInformation")
    public List<Information> findAllInformation(){
        return informationService.findAllInformation();
    }

    @RequiresPermissions("Info_maintenance")
    @RequestMapping("/insertInformation")
    public Integer insertInformation(@RequestBody Information information){
        System.out.println(information);
        Integer info = informationService.insertInformation(information);
        return info;
    }
    @RequiresPermissions("Info_maintenance")
    @RequestMapping("/deleteInformation")
    public void deleteInformation(@RequestBody Information information){

        informationService.deleteInformation(information.getInid());
        System.out.println(information.getInid());
    }
    @RequiresPermissions("Info_maintenance")
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
