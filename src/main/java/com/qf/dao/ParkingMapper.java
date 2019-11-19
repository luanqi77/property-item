package com.qf.dao;

import com.qf.domain.Parking;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/15
 * @Time: 14:09
 */
@Mapper
public interface ParkingMapper {
    //根据userId来查看车位信息
    List<Parking> selectParkingById(Integer userId);
}

