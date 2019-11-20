package com.qf.service.impl;

import com.qf.dao.ParkingSpacesMapper;
import com.qf.domain.Information;
import com.qf.domain.UserParking;
import com.qf.response.ResponseUser;
import com.qf.service.ParkingSpacesService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/19
 * @Time: 15:14
 */
@Service
public class parkingSpacesServiceImpl implements ParkingSpacesService {
    @Autowired
    private ParkingSpacesMapper parkingSpacesMapper;

    @Override
    public ResponseUser findAllParkingSpaces(Integer page, Integer size) {
        Map<String, Object> data = new HashedMap();
        data.put("page",(page-1)*size);
        data.put("size",size);
        List<UserParking> allParkingSpaces = parkingSpacesMapper.findAllParkingSpaces(data);
        ResponseUser user = new ResponseUser();
        user.setList(allParkingSpaces);
        long bytotal = parkingSpacesMapper.selectParkingSpacesBytotal();
        user.setTotal(bytotal);
        return user;
    }

    @Override
    public UserParking selectParkingSpacesById(Integer up_id) {
        return parkingSpacesMapper.selectParkingSpacesById(up_id);
    }

    @Override
    public Integer updateParkingSpaces(UserParking userParking) {
        Integer spaces = parkingSpacesMapper.updateParkingSpaces(userParking);
        return spaces;
    }

}
