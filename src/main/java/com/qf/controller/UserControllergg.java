package com.qf.controller;

import com.qf.domain.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/14
 * @Time: 11:08
 */
@RestController
public class UserControllergg {
    @Autowired
    private UserService userService;

    @RequestMapping("/findUsers")
    public List<User> findUsers(@RequestBody User user){
        return userService.findUsers();
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public Integer updateUser(@RequestBody User user){
        Integer updateUser = userService.updateUser(user);
        return  updateUser;
    }

    @RequestMapping("/selectUserById")
    public Integer selectUserById(Integer userId){
        return userService.selectUserById(userId);
    }
}
