package com.qf.service.impl;

import com.qf.dao.ParkingMapper;
import com.qf.domain.Parking;
import com.qf.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/15
 * @Time: 14:00
 */
@Service
public class ParkingServiceImpl implements ParkingService {
    @Resource
    private ParkingMapper parkingMapper;
    @Override
    public List<Parking> selectParkingById(Integer userId) {

            return parkingMapper.selectParkingById(userId);
        }
    }

