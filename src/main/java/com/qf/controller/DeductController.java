package com.qf.controller;

import com.qf.domain.Deduct;
import com.qf.service.DeductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//收费策略的修改
@RestController
public class DeductController {
    @Autowired
    private DeductService deductService;
    //获得收费策略基本信息
    @RequestMapping("/getDeduct")
    public Deduct getDeduct(){
        Deduct deduct = deductService.getDeduct();
        return deduct;
    }
    //更改收费策略基本信息
    @RequestMapping("/updateDeduct")
    public String updateDeduct(@RequestBody Deduct deduct){
        //System.out.println(deduct);
        return deductService.updateDeduct(deduct);
    }

    @RequestMapping("/warnJob")
    public String warnJob(){
        return null;
    }
}
