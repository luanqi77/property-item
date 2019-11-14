package com.qf.service;

import com.qf.bean.UserAccount;
import com.qf.bean.UserRegisterRequest;
import com.qf.domain.Staff;
import com.qf.domain.User;

import java.util.List;

public interface BackstageService {
    String login(Staff staff);
    String updateStaffPassword(Staff staff);

    String insertUser(UserRegisterRequest userRegisterRequest);
    int delUserById(int id);
    int updateUser(User user);
    List<UserAccount> findUserAccount();

}
