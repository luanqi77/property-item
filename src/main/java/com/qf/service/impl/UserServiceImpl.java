package com.qf.service.impl;

import com.qf.dao.UserMapper;
import com.qf.dao.UserResponsitory;
import com.qf.domain.User;
import com.qf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName:com.qf.service.impl;
 * @ClassName:UserServiceImpl;
 * @author:马浩雲
 * @date2019/11/1114:38
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserResponsitory userResponsitory;

    @Autowired
    private UserMapper userMapper;


    @Override
    public String login(User user) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            subject.login(token);
            if (subject.isAuthenticated()) {
                return "succeed";
            } else {
                return "fail";
            }
        } catch (Exception e) {
            System.out.println("登录失败，密码错误");
            e.printStackTrace();
        }
        return "login";
    }

    @Override
    public String regist(User user) {
        if (user.getCode() != null) {

            User users = userResponsitory.findUserByTel(user.getTel());

            if (user.getCode().equals(users.getCode())) {
                ByteSource bytes = ByteSource.Util.bytes(user.getUsername());
                SimpleHash md5 = new SimpleHash("MD5", user.getPassword(), bytes, 1024);
                user.setPassword(md5.toString());
                int i = userMapper.updateByTel(user);
                if (i > 0) {
                    return "注册成功！";
                } else {
                    return "修改失败";
                }
            } else {
                return "验证码错误！";
            }

        } else {
            return "验证码为空";
        }

    }


    @Override
    public User checkName(String username) {
        return userResponsitory.findUserByUsername(username);
    }

    @Override
    public User checkTel(String tel) {

        return userResponsitory.findUserByTel(tel);
    }

    @Override
    public List<User> findUsers() {
        return userMapper.findUsers();
    }

    @Override
    public Integer updateUser(User user) {
        if (user.getUsername() != "") {
            Integer user1 = userMapper.updateUser(user);
            return user1;
        } else {
            return null;
        }

    }

    @Override
    public Integer selectUserById(Integer userId) {

        return userMapper.selectUserById(userId);
    }

    @Override
    public User checkOpenId(String openId) {
        return userResponsitory.findUserByOpenId(openId);
    }

    @Override
    public User updateUserOpenId(User user) {
        return userResponsitory.saveAndFlush(user);
    }
}
