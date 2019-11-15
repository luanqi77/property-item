package com.qf.dao;

import com.qf.domain.Parking;
import com.qf.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateByTel(User user);

//通过userId来查找用户
    User findOneUsers(Integer userId);

//修改User
    Integer updateUser(User user);

//根据userId来查看车位信息
    List<Parking> selectUserById(Integer userId);

    List<User> findUserToPay();


}