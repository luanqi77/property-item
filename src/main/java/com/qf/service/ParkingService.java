package com.qf.service;

import com.qf.domain.Parking;

import java.util.List;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/15
 * @Time: 11:58
 */
public interface ParkingService {
    List<Parking> selectParkingById(Integer userId);
}
