package com.qf.controller;

import com.qf.domain.Apply;
import com.qf.domain.ApplyByPageAndSize;
import com.qf.domain.Applysss;
import com.qf.domain.Staff;
import com.qf.service.ApplyService;

import com.qf.service.StaffService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

/**
 * @PackageName:com.qf.controller;
 * @ClassName:ApplyController;
 * @author:马浩雲
 * @date2019/11/1411:18
 */
@RestController
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private StaffService staffService;


    /**
     * 用户报修信息存储
     */
    @RequestMapping(value = "/userApply",method = RequestMethod.POST)
    public String userApply(@RequestBody Apply apply){
        if(apply.getUserId()!=null && apply.getApplyDeso()!=null ){
            apply.setStatus(1);
            apply.setApplyTime(new Date());
            Apply add = applyService.add(apply);
            if(add!=null){
                return "ok";
            }else {
                return "error";
            }
        }
        return "信息不完整";
    }
/*    *//**
     *员工对报修信息的查看(根据status)
     *//*
    @RequestMapping(value = "/selectApplyByStaff/{page}/{size}",method = RequestMethod.GET)
    public List<Applysss> selectApplyByStaff(@RequestBody Apply apply,@PathVariable("page") Integer page, @PathVariable("size") Integer size){

            return applyService.selectApplyByStaff(apply.getStatus());

    }*/
    /**
     *员工对报修信息的查看(根据status)
     */
    @RequiresPermissions(" Maintenance_application")
    @RequestMapping(value = "/selectApplyByStaff/{status}/{page}/{size}",method = RequestMethod.GET)
    public ApplyByPageAndSize selectApplyByStaff(@PathVariable("status") Integer status, @PathVariable("page") Integer page, @PathVariable("size") Integer size){

        return applyService.ApplyFindAll(status,page,size);

    }
    /**
    * 员工修改报修信息表状态
    */
    @RequiresPermissions(" Maintenance_application")
    @RequestMapping("/updateApplyStatus")
    public String updateApplyStatus(@RequestBody Apply apply11){
        Integer applyId = apply11.getApplyId();
        String staffNumber =(String) SecurityUtils.getSubject().getPrincipal();
        //(postman测试用) String staffNumber ="admin";
        if(staffNumber!=null && applyId!=null){
            Staff staff = staffService.selectStaffByStaffNumber(staffNumber);
            Apply apply = applyService.selectApplyByApplyId(applyId);
            apply.setStatus(2);
            apply.setStaffName(staff.getStaffName());
            Apply apply1 = applyService.updateApplyByStatus(apply);
            if(apply1!=null){
                return "ok";
            }

        }
            return "error";
    }


}
