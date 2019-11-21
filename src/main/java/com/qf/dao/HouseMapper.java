package com.qf.dao;

import com.qf.domain.Deduct;
import com.qf.domain.House;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseMapper {
    int deleteByPrimaryKey(Integer houseId);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Integer houseId);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    int removeMaster(Integer userId);

    int updateFee(Deduct deduct);

    House selectByUserId(Integer userId);

    Integer selectNoArrears(Integer build);

    Integer selectHousehold(Integer build);
}