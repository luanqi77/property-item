package com.qf.dao;

import com.qf.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
       int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateByTel(User user);

}