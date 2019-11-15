package com.qf.dao;

import com.qf.domain.LogInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogInfoMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(LogInfo record);

    int insertSelective(LogInfo record);

    LogInfo selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(LogInfo record);

    int updateByPrimaryKey(LogInfo record);
}