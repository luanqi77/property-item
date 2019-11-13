package com.qf.controller;

import com.qf.domain.User;
import com.qf.service.RegisterService;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @PackageName:com.qf.controller;
 * @ClassName:UserController;
 * @author:马浩雲
 * @date2019/11/1111:36
 */
//@Controller
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;


    /*
     * 用户登录校验(shiro验证)
     * 返回：succeed表成功，fail表失败 login表
     *
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String UserLogin(@RequestBody User user) {

        return userService.login(user);
    }

    /*
     *  用户注册
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(@RequestBody User user) {


        return userService.regist(user);
    }

    /*
     * 验证用户名是否唯一
     * */
    @RequestMapping("/checkName")
    public String checkName(@RequestBody User user) {

        if (userService.checkName(user.getUsername()) != null) {
            return "succeed";
        }

        return "fail";
    }

    /*
     * 验证手机号是否唯一
     * */
    @RequestMapping("/checkTel")
    public String checkName(@PathVariable("tel") String tel) {
        User user = userService.checkTel(tel);
        if (user != null) {
            return "succeed";
        }
        return "fail";
    }

    /*
     * 发送手机验证码
     * */
    @RequestMapping("/sendTelMessage/{phone}")
    public String sendTelMessage(@PathVariable("phone") String tel) {
        String s = registerService.sendTelMessage(tel);
        if (s != null) {
            return "发送成功！";
        }
        return "发送失败,此手机号不是预留手机号";
    }

    /*
    * 微信扫码登录
    * */
//    RequestMapping
}
