package com.qf.service;

import com.qf.domain.House;

/**
 * @Description:
 * @Company: 憨憨互联
 * @Author: 张朝
 * @Date: 2019/11/18
 * @Time: 16:18
 */
public interface HouseService {
    House selectByUserId(Integer id);
}
