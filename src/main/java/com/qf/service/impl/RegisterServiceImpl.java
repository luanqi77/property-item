package com.qf.service.impl;


import com.qf.dao.UserResponsitory;
import com.qf.domain.User;
import com.qf.service.RegisterService;
import com.qf.utils.TelMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private TelMessageUtils telMessageUtils;
    @Autowired
    private UserResponsitory userResponsitory;

    @Override
    public String sendTelMessage(String tel) {
        User userByTel = userResponsitory.findUserByTel(tel);
        if(userByTel!=null){
            String checkCode = telMessageUtils.sendMessage(tel);
            User user =new User();
            user.setCode(checkCode);
            user.setTel(tel);
            User save = userResponsitory.save(user);
            return checkCode;
        }else {
            return null;
        }

    }
}
