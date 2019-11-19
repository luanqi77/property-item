package com.qf.dao;

import com.qf.domain.Advise;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdviseMapper {
    int deleteByPrimaryKey(Integer adviseId);

    int insert(Advise record);

    int insertSelective(Advise record);

    Advise selectByPrimaryKey(Integer adviseId);

    int updateByPrimaryKeySelective(Advise record);

    int updateByPrimaryKey(Advise record);

    void updateStatusByAdviseId(Advise advise);
}