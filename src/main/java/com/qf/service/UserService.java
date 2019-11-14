package com.qf.service;

import com.qf.domain.User;

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

   User checkOpenId(String openId);

   User updateUser(User user);


}
