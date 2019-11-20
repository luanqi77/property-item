package com.qf.controller;

import com.alipay.api.AlipayApiException;
import com.qf.utils.AlipayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

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

    @RequestMapping("/propertypay")
    public String PropertyPay(){

        String pay="";
        try {
            pay = alipayUtils.pay();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(pay);
        return pay;
    }
}
