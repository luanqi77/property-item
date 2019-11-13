package com.qf.service.impl;

import com.qf.dao.AccountMapper;
import com.qf.dao.DeductMapper;
import com.qf.dao.HouseMapper;
import com.qf.dao.UserMapper;
import com.qf.domain.Deduct;
import com.qf.domain.User;
import com.qf.service.DeductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeductServiceImpl implements DeductService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private DeductMapper deductMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public Deduct getDeduct() {
        Deduct deduct = deductMapper.getDeduct();
        return deduct;
    }

    @Override
    public String updateDeduct(Deduct deduct) {
        if (deductMapper.updateDeduct(deduct)>0){
            if (deduct.getHotFee()!=null||deduct.getPropertyFee()!=null){
                houseMapper.updateFee(deduct);
                return "success";
            }
        }
        return "fail";
    }

    @Override
    public List<User> warnJob() {
        List<User> users = userMapper.findUserToPay();

        return users;
    }

    @Override
    public String payJob() {

        return null;
    }
}
