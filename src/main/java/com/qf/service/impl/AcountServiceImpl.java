package com.qf.service.impl;

import com.qf.dao.AccountMapper;
import com.qf.service.AcountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/20
 * @Time: 17:34
 */
@Service
public class AcountServiceImpl implements AcountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public Integer save(Integer userId) {
        return accountMapper.save(userId);
    }

    @Override
    public Double selectMoneyByUserId(Integer userId) {
        return accountMapper.selectMoneyByUserId(userId);
    }
}
