package com.qf.service.impl;

import com.qf.dao.AccountMapper;
import com.qf.dao.DeductMapper;
import com.qf.dao.HouseMapper;
import com.qf.dao.UserMapper;
import com.qf.domain.Deduct;
import com.qf.domain.User;
import com.qf.service.DeductService;
import com.qf.utils.SchedulerUtils;
import com.qf.utils.TelMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeductServiceImpl implements DeductService {
    @Autowired
    private TelMessageUtils telMessageUtils;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private DeductMapper deductMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private SchedulerUtils schedulerUtils;

    @Override
    public Deduct getDeduct() {
        Deduct deduct = deductMapper.getDeduct();
        return deduct;
    }

    @Override
    public String updateDeduct(Deduct deduct) {
        if (deductMapper.updateDeduct(deduct) > 0) {
            if (deduct.getHotFee() != null && deduct.getPropertyFee() != null && deduct.getWarnTime() != null && deduct.getDeductTime() != null) {
                houseMapper.updateFee(deduct);
                try {
                    Integer deductTime = deduct.getDeductTime();
                    Integer warnTime = deduct.getWarnTime();
                    String cronString = "0 0 0 " + deductTime + " * ? *";
                    String cronString2 = "0 0 0 " + warnTime + " * ? *";
                    schedulerUtils.updateExecuteTime("payJob", cronString);
                    schedulerUtils.updateExecuteTime("warnJob", cronString2);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return "fail";
    }

    @Override
    public String warnJob() {
        List<User> users = userMapper.findUserToPay();
        return telMessageUtils.warnMessage(users);
    }

    @Override
    public String payJob() {
        if (accountMapper.payFee()>0){
            System.out.println("本月费用已扣除");
            return "success";
        }
        return null;
    }
}
