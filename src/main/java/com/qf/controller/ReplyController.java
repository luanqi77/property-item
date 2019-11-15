package com.qf.controller;

import com.qf.domain.Reply;
import com.qf.response.ResponseUser;
import com.qf.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    /**
     * 展示用户的建议及投诉*/
    @RequestMapping("/adviseFindAll/{page}/{size}")
    public ResponseUser adviseFindAll(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        return replyService.adviseFindAll(page,size);
    }
    /**
     * 员工回复业主的投诉建议及修改建议表的回复状态*/
    @RequestMapping("/insertReply")
    public String insertReply(@RequestBody Reply reply){
        if (reply!=null){
           return replyService.insertReply(reply);
        }
        return "fail";
    }
}
