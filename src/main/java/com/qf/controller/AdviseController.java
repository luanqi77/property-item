package com.qf.controller;

import com.qf.domain.Advise;
import com.qf.service.AdviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AdviseController {
    @Autowired
    private AdviseService adviseService;
    /*
    * 用户提交建议及投诉
    * */
    @RequestMapping(value = "/insertAdvise",method = RequestMethod.POST)
    public String insertAdvise(@RequestBody Advise advise, HttpSession session){
        if (!advise.getDescription().isEmpty()){
            //User user = (User)session.getAttribute("user");
            //advise.setUserId(user.getUserId);
            advise.setUserId(1);
            return adviseService.insertAdvise(advise);
        }else {
            return "fail";
        }
    }

}
