package com.qf.controller;

import com.qf.domain.Staff;
import com.qf.utils.Md5Utils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiresPermissions("operation_log")
public class TestController {
    @Autowired
    private Md5Utils md5Utils;
    @RequestMapping("/test")
    public String test(@RequestBody Staff staff){
        String passwordCode = md5Utils.getPasswordCode(staff.getPassword(), staff.getStaffNumber());
        return passwordCode;
    }
}
