package com.qf.dao;


import com.qf.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserResponsitory extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);

    User findUserByTel(String tel);

    User findUserByOpenId(String openId);

    User findUserByUsernameAndTel(String username,String tel);

    User findUserByUserId(Integer userId);




}
