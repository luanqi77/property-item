package com.qf.controller;

import com.qf.domain.Parking;
import com.qf.domain.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/findOneUsers")
    public User findOneUsers(@RequestBody User user){
        User oneUser = userService.findOneUsers(user.getUserId());
        return oneUser;
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public Integer updateUser(@RequestBody User user){
        Integer updateUser = userService.updateUser(user);
        return  updateUser;
    }

    @RequestMapping("/selectUserById/{userId}")
    public List<Parking> selectUserById(@PathVariable("userId") Integer userId){
        return userService.selectUserById(userId);

    }
}
