package com.qf.controller;

import com.qf.dao.AccountMapper;
import com.qf.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Company: 憨憨互联
 * @Author: 张朝
 * @Date: 2019/11/20
 * @Time: 20:50
 */
@RestController
public class AccountController {
    @Autowired
    AccountMapper accountMapper;

    @RequestMapping("/getUserMoney")
    public Double getUserMoney(@RequestBody User userId){
        Double res = accountMapper.selectMoneyByUserId(userId.getUserId());
        return res;
    }
}
