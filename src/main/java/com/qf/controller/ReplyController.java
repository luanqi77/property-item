package com.qf.controller;

import com.qf.aop.SystemControllerLog;
import com.qf.bean.PageBeanFindAdviseAndReply;
import com.qf.domain.AdviseAndReply;
import com.qf.domain.Reply;
import com.qf.domain.Staff;
import com.qf.response.ResponseUser;
import com.qf.service.ReplyService;
import com.qf.service.StaffService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    @Autowired
    private StaffService staffService;
    /**
     * 展示用户的建议及投诉*/
    @RequiresPermissions("complaint")
    @RequestMapping("/adviseFindAll/{page}/{size}")
    public ResponseUser adviseFindAll(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return replyService.adviseFindAll(page,size);
    }
    /**
     * 员工回复业主的投诉建议及修改建议表的回复状态*/
    @SystemControllerLog(methods = "回复投诉")
    @RequestMapping(value = "/insertReply",method = RequestMethod.POST)
    @RequiresPermissions("complaint")
    public String insertReply(@RequestBody Reply reply){
        String staffNumber = (String) SecurityUtils.getSubject().getPrincipal();
        Staff staff = staffService.selectStaffByStaffNumber(staffNumber);
            if (reply.getAdviseId()!=null&&reply.getReplyDeso()!=null){
                reply.setStaffId(staff.getStaffId());
                return replyService.insertReply(reply);
            }
                return "fail";
    }

    /*员工查看自己的回复,根据自己id查并分页*/
    @RequestMapping("/selectReplyByStaffId/{page}/{size}")
    @RequiresPermissions("complaint")
    public PageBeanFindAdviseAndReply selectReplyByStaffId(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        String staffNumber = (String) SecurityUtils.getSubject().getPrincipal();
        Staff staff = staffService.selectStaffByStaffNumber(staffNumber);
        Integer staffId=staff.getStaffId();
        if (staffId!=null){
            return replyService.selectReplyByStaffId(page,size,staffId);
        }
        return null;

    }

    /*
    * 用户查看回复*/
    @RequestMapping("/selectReplyByAdviseId")
    public List<Reply> selectReplyByAdviseId(@RequestParam("adviseId") Integer adviseId){
        return replyService.selectReplyByAdviseId(adviseId);
    }

}
