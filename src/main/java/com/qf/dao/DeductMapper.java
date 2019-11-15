package com.qf.dao;

import com.qf.domain.Deduct;

public interface DeductMapper {
    int deleteByPrimaryKey(Integer deductId);

    int insert(Deduct record);

    int insertSelective(Deduct record);

    Deduct selectByPrimaryKey(Integer deductId);

    int updateByPrimaryKeySelective(Deduct record);

    int updateByPrimaryKey(Deduct record);
}