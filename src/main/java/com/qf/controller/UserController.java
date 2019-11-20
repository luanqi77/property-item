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
import com.qf.utils.UploadUtils;
import javafx.geometry.Pos;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @PackageName:com.qf.controller;
 * @ClassName:UserController;
 * @author:马浩雲
 * @date2019/11/1111:36
 */
@Controller
//@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private WeixinLoginService weixinLoginService;

    @Autowired
    private UploadUtils uploadUtils;


    /**
     * 用户登录校验(shiro验证)
     * 返回：succeed表成功，fail表失败 login表
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public String UserLogin(@RequestBody User user, HttpSession httpSession) {
        User login = userService.login(user);
        if (login!=null) {
            httpSession.setAttribute("user", login);
            return "succeed";
        }
        return "fail";
    }

    /**
     * 在seesion取用户信息
     */
    @RequestMapping("/findSession")
    @ResponseBody
    public User findSession(HttpSession session) {
        System.out.println(session.getId());
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        return user;
    }

    /**
     * 用户注销，存在session里的user注销掉
     */
    @RequestMapping("/userlogout")
    @ResponseBody
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "1";
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    public String regist(@RequestBody User user) {


        return userService.regist(user);
    }

    /**
     * 验证用户名是否唯一
     */
    @RequestMapping(value = "/checkName", method = RequestMethod.POST)
    @ResponseBody
    public String checkName(@RequestBody User user) {

        if (userService.checkName(user.getUsername()) != null) {
            return "succeed";
        }

        return "fail";
    }

    /**
     * 验证手机号是否唯一
     */
    @RequestMapping(value = "/checkTel", method = RequestMethod.POST)
    @ResponseBody
    public String checkTel(@RequestBody User user) {
        String tel = user.getTel();
        User user1 = userService.checkTel(tel);
        if (user1 != null) {
            return "succeed";
        }
        return "fail";
    }

    /**
     * 发送手机验证码
     */
    @RequestMapping(value = "/sendTelMessage", method = RequestMethod.POST)
    @ResponseBody
    public String sendTelMessage(@RequestBody User user) {
        String s = registerService.sendTelMessage(user.getTel());
        if (s != null) {
            return "发送成功！";
        }
        return "发送失败,此手机号不是预留手机号";
    }

    /**
     * 微信扫码登录
     */
    @RequestMapping("/weixinLogin")
    @ResponseBody
    public String wxLogin() {
        String body = null;
        System.out.println("============进来了wxLogin方法");
        String url = weixinLoginService.genLoginUrl();
        System.out.println(url);

        // 实例一个http客户端
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // 实例一个httpGet请求，url放进去
        HttpGet httpGet = new HttpGet(url);
        try {
            // 用客户端执行get请求，并得到一个response回应，这里的执行时间根据客户端请求服务器的时间来决定
            // 如连接不成功会有IO异常
            HttpResponse response = httpClient.execute(httpGet);
            // 获取response里面的内容等。
            HttpEntity entity = response.getEntity();
            // 将entity元素通过Entity工具类转化为字符串形式，此时即为url页面html的字符串,这里的UTF_8为页面的实际编码
            body = EntityUtils.toString(entity, HTTP.UTF_8);

            Document document = Jsoup.parse(body);
            //System.out.println(document);
            Element table = document.getElementsByTag("div").get(0);
            Elements a = document.select("img[class^=qrcode lightBorder]");
            String image = a.attr("src");
            String weixinpic = "https://open.weixin.qq.com" + image;
            System.out.println(weixinpic);
            return weixinpic;
            //}
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }


        return "error";
    }

    /**
     * 回调地址处理
     *//*
    @RequestMapping(value = "/weixinconnect")
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
                System.out.println(user);
                ResponseUserAndError responseUserAndError = new ResponseUserAndError();

                if (user != null) {

                    responseUserAndError.setUser(user);
                    return responseUserAndError;
                }
                responseUserAndError.setOpenId(openid);
                //return responseUserAndError;
                return null;
            }
        }
        return null;
    }*/

    /**
     * 回调地址处理
     */
    @RequestMapping(value = "/weixinconnect")
    public ModelAndView callback(String code, String state,HttpSession session) {
        System.out.println("========");
        String access_token = null;
        String openid = null;
        ModelAndView mav = new ModelAndView();
        if (code != null && state != null) {
            // 验证state为了用于防止跨站请求伪造攻击
            String decrypt = AesUtil.decrypt(AesUtil.parseHexStr2Byte(state), AesUtil.PASSWORD_SECRET_KEY, 16);
            if (!decrypt.equals(Constanst.PWD_MD5 + DateUtil.getYYYYMMdd())) {
                mav.addObject("error", "登录失败，请联系管理员！");
                mav.setViewName("loginError");
                return mav;
            }
            AccessToken access = weixinLoginService.getAccessToken(code);
            if (access != null) {
                // 把获取到的access_token和openId赋值给变量
                access_token = access.getAccess_token();
                openid = access.getOpenid();

                // 存在则把当前账号信息授权给扫码用户
                // 拿到openid获取微信用户的基本信息

                // 此处可以写业务逻辑
                WechatUserUnionID userUnionID = weixinLoginService.getUserUnionID(access_token, openid);
                User user = userService.checkOpenId(openid);
                if (user != null) {


                    mav.addObject("userInfo", userUnionID);
                    System.out.println(mav);
                    mav.setViewName("http://www.baidu.com");
                    return mav;
                }


                mav.setViewName("loginError");
                return mav;

            }
        }
        return null;

    }

    /**
     * openId绑定用户
     * 根据手机号查询openId进行绑定
     */
    @RequestMapping(value = "/checkOpenIdByTel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUserAndError checkOpenIdByTel(@RequestBody User user) {
        ResponseUserAndError responseUserAndError = new ResponseUserAndError();

        if (user != null) {
            User user1 = userService.checkTel(user.getTel());

            if (user1 != null) {
                if (user1.getOpenId() == null) {

                    if (user.getCode().equals(user1.getCode())) {
                        user1.setOpenId(user.getOpenId());
                        User user2 = userService.updateUserOpenId(user1);

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

    /**
     * 找回密码(重置密码)
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public String resetPassword(@RequestBody User user) {
        String username = user.getUsername();
        String tel = user.getTel();
        String code = user.getCode();
        System.out.println(username + "==" + tel + "==" + code);

        if (user.getUsername() != null && user.getTel() != null) {

            User userByUsernameAndTel = userService.findUserByUsernameAndTel(user);
            System.out.println(userByUsernameAndTel);

            if (userByUsernameAndTel != null) {

                if (user.getCode().equals(userByUsernameAndTel.getCode())) {
                    userByUsernameAndTel.setPassword("111111");
                    ByteSource bytes = ByteSource.Util.bytes(userByUsernameAndTel.getUsername());
                    SimpleHash md5 = new SimpleHash("MD5", userByUsernameAndTel.getPassword(), bytes, 1024);
                    userByUsernameAndTel.setPassword(md5.toString());
                    User user1 = userService.updateUserOpenId(userByUsernameAndTel);
                    if (user1 != null) {
                        return "ok";
                    }
                    return "error";
                }
            }

        }
        return "error";
    }

    /**
     * 修改用户信息，先查在改
     */
    @RequestMapping("/findUserById")
    @ResponseBody
    public User findUserById(@RequestBody User user) {
        User userById = userService.findUserById(user);
        if (userById != null) {
            return user;

        }
        return null;
    }

    /**
     * 查完修改，
     */
    @RequestMapping("/saveAndFlushUser")
    @ResponseBody
    public String updateUser(@RequestBody User user) {
        if (user != null && user.getUserId() != null) {
            User user1 = userService.updateUserOpenId(user);
            return "ok";
        }

        return "error";
    }

    /**
     * 小图上传
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file) {

        if (Objects.isNull(file) || file.isEmpty()) {
            return "fail";
        } else {
            String path = uploadUtils.upload(file);
            return path;
        }

    }
    /**
     * 查看我的钱包
     * */
    @RequestMapping("checkUserPassword")
    public String checkUserPassword(@RequestBody User user,HttpSession session){

        if(user.getPassword()!=null){

            User user1 = (User) session.getAttribute("user");
            if(user.getPassword()==user1.getPassword()){
                return "succeed";
            }
            return "error";
        }
        return "error";
    }

}
