package com.qf.controller;

import com.qf.domain.Apply;
import com.qf.domain.Applysss;
import com.qf.service.ApplyService;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



    /**
     * 用户报修信息存储
     */
    @RequestMapping(value = "/userApply",method = RequestMethod.POST)
    public String userApply(@RequestBody Apply apply){
        if(apply.getUserId()!=null && apply.getApplyDeso()!=null && apply.getApplyTime()!=null){
            apply.setStatus(1);
            Apply add = applyService.add(apply);
            if(add!=null){
                return "ok";
            }else {
                return "error";
            }
        }
        return "信息不完整";
    }
    /**
     *员工对报修信息的查看(根据status)
     */
    @RequestMapping(value = "/selectApplyByStaff/{status}",method = RequestMethod.GET)
    public List<Applysss> selectApplyByStaff(@PathVariable("status")String status){

            return applyService.selectApplyByStaff(status);

    }
    /**
    * 员工修改报修信息表状态
    */
    @RequestMapping("/updateApplyStatus")
    public String updateApplyStatus(){
        Object staffNumber = SecurityUtils.getSubject().getPrincipal();
        if(staffNumber!=null){
            return "";

        }
            return "";
    }


}
