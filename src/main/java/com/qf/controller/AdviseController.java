package com.qf.controller;

import com.qf.bean.PageBean;
import com.qf.domain.Advise;
import com.qf.domain.User;
import com.qf.service.AdviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            User user = (User)session.getAttribute("user");
            advise.setUserId(user.getUserId());
            return adviseService.insertAdvise(advise);
        }else {
            return "fail";
        }
    }

    /*
    * 查询用户建议*/
    @RequestMapping(value = "/selectAdviseByAdviseId",method = RequestMethod.POST)
    public Advise selectAdviseByAdviseId(@RequestBody Advise advise){
      return  adviseService.selectAdviseByadviseId(advise.getAdviseId());
    }


}
