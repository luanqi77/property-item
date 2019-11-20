package com.qf.controller;

import com.alipay.api.AlipayApiException;
import com.qf.domain.User;
import com.qf.service.AcountService;
import com.qf.utils.AlipayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/14
 * @Time: 19:24
 */
@RestController
public class PropertyController {
    @Autowired
    private AlipayUtils alipayUtils;
    @Autowired
   private AcountService acountService;
    @RequestMapping( "/propertypay")
   public String PropertyPay(HttpSession httpSession){
        User user =(User) httpSession.getAttribute("user");
        Integer userId = user.getUserId();
        acountService.save(userId);
        String pay="";
        try {
            pay = alipayUtils.pay();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return pay;
    }
}
