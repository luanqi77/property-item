package com.qf.service.impl;

import com.qf.dao.HouseMapper;
import com.qf.domain.House;
import com.qf.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Company: 憨憨互联
 * @Author: 张朝
 * @Date: 2019/11/18
 * @Time: 16:19
 */
@Service
public class HouseServiceImpl implements HouseService{
    @Autowired
    private HouseMapper houseMapper;

    @Override
    public House selectByUserId(Integer id) {
        House house = houseMapper.selectByUserId(id);
        return house;
    }
}
