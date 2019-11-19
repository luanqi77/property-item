package com.qf.service;

import com.qf.domain.User;

import java.util.List;

/**
 * @PackageName:com.qf.service;
 * @ClassName:UserService;
 * @author:马浩雲
 * @date2019/11/1114:37
 */

public interface UserService {

    User checkName(String username);

    User checkTel(String tel);

    String login(User user);

    String regist(User user);

    //高健
    List<User> findUsers();

    Integer updateUser(User user);

    Integer selectUserById(Integer userId);

    //高健end
    User checkOpenId(String openId);

    User updateUserOpenId(User user);

    User findUserByUsernameAndTel(User user);

    User  findUserById(User user);



}
