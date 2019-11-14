package com.qf.dao;

import com.qf.domain.Apply;
import com.qf.domain.Applysss;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplyMapper {
    int deleteByPrimaryKey(Integer applyId);

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(Integer applyId);

    List<Applysss> selectApplyByStaff(String status);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);
}