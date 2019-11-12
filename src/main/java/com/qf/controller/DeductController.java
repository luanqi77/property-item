package com.qf.controller;

import com.qf.domain.Deduct;
import com.qf.service.DeductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeductController {
    @Autowired
    private DeductService deductService;
    @RequestMapping("/getDeduct")
    public Deduct getDeduct(){
        Deduct deduct = deductService.getDeduct();
        return deduct;
    }
    @RequestMapping("/updateDeduct")
    public String updateDeduct(@RequestBody Deduct deduct){
        System.out.println(deduct);
        return deductService.updateDeduct(deduct);
    }
}
