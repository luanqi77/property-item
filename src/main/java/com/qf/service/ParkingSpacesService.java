package com.qf.service;

import com.qf.domain.UserParking;
import com.qf.response.ResponseUser;

import java.util.List;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/19
 * @Time: 15:01
 */
public interface ParkingSpacesService {
    ResponseUser findAllParkingSpaces(Integer page, Integer size);

    UserParking selectParkingSpacesById(Integer up_id);

    Integer updateParkingSpaces(UserParking userParking);
}
