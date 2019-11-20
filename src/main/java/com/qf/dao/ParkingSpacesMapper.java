package com.qf.dao;

import com.qf.domain.UserParking;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/19
 * @Time: 15:19
 */
@Mapper
public interface ParkingSpacesMapper {
    List<UserParking> findAllParkingSpaces(Map<String,Object> data);

    Integer selectParkingSpacesBytotal();

    UserParking selectParkingSpacesById(Integer up_id);

    Integer updateParkingSpaces(UserParking userParking);
}
