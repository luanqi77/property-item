package com.qf.service.impl;

import com.qf.dao.DeductMapper;
import com.qf.domain.Deduct;
import com.qf.service.DeductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeductServiceImpl implements DeductService {
    @Autowired
    private DeductMapper deductMapper;
    @Override
    public Deduct getDeduct() {
        Deduct deduct = deductMapper.getDeduct();
        return deduct;
    }

    @Override
    public String updateDeduct(Deduct deduct) {
        if (deductMapper.updateDeduct(deduct)>0){
            return "更改成功";
        }
        return null;
    }
}
