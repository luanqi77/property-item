package com.qf.controller;


import com.qf.constanst.Constanst;
import com.qf.domain.Apply;
import com.qf.domain.User;
import com.qf.entity.AccessToken;
import com.qf.entity.WechatUserUnionID;
import com.qf.response.ResponseUserAndError;
import com.qf.service.ApplyService;
import com.qf.service.RegisterService;
import com.qf.service.UserService;
import com.qf.service.WeixinLoginService;
import com.qf.util.AesUtil;
import com.qf.util.DateUtil;
import com.qf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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

    @Autowired
    private WeixinLoginService weixinLoginService;



    /**
     * 用户登录校验(shiro验证)
     * 返回：succeed表成功，fail表失败 login表
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String UserLogin(@RequestBody User user, HttpSession httpSession) {
        String login = userService.login(user);
        if (login.equals("succeed")) {
            httpSession.setAttribute("user", user);
            return "succeed";
        }
        return login;
    }
    /**
     * 在seesion取用户信息
     */
    @RequestMapping("/findSession")
    public User findSession(HttpSession session){
        User user = (User)session.getAttribute("user");
        return user;
    }

    /**
     * 用户注销，存在session里的user注销掉
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "1";
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(@RequestBody User user) {


        return userService.regist(user);
    }

    /**
     * 验证用户名是否唯一
     */
    @RequestMapping("/checkName")
    public String checkName(@RequestBody User user) {

        if (userService.checkName(user.getUsername()) != null) {
            return "succeed";
        }

        return "fail";
    }

    /**
     * 验证手机号是否唯一
     */
    @RequestMapping("/checkTel/{tel}")
    public String checkName(@PathVariable("tel") String tel) {
        User user = userService.checkTel(tel);
        if (user != null) {
            return "succeed";
        }
        return "fail";
    }

    /**
     * 发送手机验证码
     */
    @RequestMapping("/sendTelMessage/{phone}")
    public String sendTelMessage(@PathVariable("phone") String tel) {
        String s = registerService.sendTelMessage(tel);
        if (s != null) {
            return "发送成功！";
        }
        return "发送失败,此手机号不是预留手机号";
    }

    /**
     * 微信扫码登录
     */
    @RequestMapping("/wxLogin")
    public String wxLogin() {
        System.out.println("============进来了wxLogin方法");
        String url = weixinLoginService.genLoginUrl();
        System.out.println(url);
        return url;
    }

    /**
     * 回调地址处理
     */
    @RequestMapping(value = "/weixinconnect", method = RequestMethod.GET)
    public ResponseUserAndError callback(String code, String state) {
//        System.out.println("===========callback方法");
//        System.out.println(code+"====="+state);
        String access_token = null;
        String openid = null;
        ModelAndView mav = new ModelAndView();
        if (code != null && state != null) {
            // 验证state为了用于防止跨站请求伪造攻击
            String decrypt = AesUtil.decrypt(AesUtil.parseHexStr2Byte(state), AesUtil.PASSWORD_SECRET_KEY, 16);
            if (!decrypt.equals(Constanst.PWD_MD5 + DateUtil.getYYYYMMdd())) {
                ResponseUserAndError responseUserAndError = new ResponseUserAndError();
                responseUserAndError.setError("登录失败，请联系管理员");
                return responseUserAndError;
            }
            AccessToken access = weixinLoginService.getAccessToken(code);
            if (access != null) {
                // 把获取到的access_token和openId赋值给变量
                access_token = access.getAccess_token();
                openid = access.getOpenid();
//                System.out.println(access_token+"==="+openid);

                // 存在则把当前账号信息授权给扫码用户
                // 拿到openid获取微信用户的基本信息

                // 此处可以写业务逻辑

                WechatUserUnionID userUnionID = weixinLoginService.getUserUnionID(access_token, openid);

//                System.out.println(userUnionID.toString());

                User user = userService.checkOpenId(openid);
                ResponseUserAndError responseUserAndError = new ResponseUserAndError();

                if (user != null) {

                    responseUserAndError.setUser(user);
                    return responseUserAndError;
                }
                responseUserAndError.setOpenId(openid);
                return responseUserAndError;
            }
        }
        return null;
    }

    /**
     * openId绑定用户
     * 根据手机号查询openId进行绑定
     */
    @RequestMapping("/checkOpenIdByTel")
    public ResponseUserAndError checkOpenIdByTel(@RequestBody User user) {
        ResponseUserAndError responseUserAndError = new ResponseUserAndError();

        if (user != null) {
            User user1 = userService.checkTel(user.getTel());

            if (user1 != null) {
                if (user1.getOpenId() == null) {

                    if (user.getCode().equals(user1.getCode())) {
                        user1.setOpenId(user.getOpenId());
                        User user2 = userService.updateUser(user);

                        if (user2 != null) {
                            responseUserAndError.setUser(user2);
                            return responseUserAndError;
                        }
                    } else {
                        responseUserAndError.setError("验证码错误");
                        return responseUserAndError;
                    }
                }
            } else {
                responseUserAndError.setError("该手机号不是预留手机号");
                return responseUserAndError;
            }
        }
        responseUserAndError.setError("信息不完整");
        return responseUserAndError;

    }



}
