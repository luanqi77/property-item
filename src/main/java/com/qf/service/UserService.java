package com.qf.service;

import com.qf.domain.Parking;
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

    User findOneUsers(Integer userId);

    Integer updateUser(User user);

    List<Parking> selectUserById(Integer userId);
}
