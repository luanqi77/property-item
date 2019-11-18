package com.qf.dao;

import com.qf.domain.Information;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/12
 * @Time: 11:00
 */
@Mapper
public interface InformationMapper {
    List<Information> findAllInformation();

    List<Information> findAllInformationBypage(Map<String,Object> data);

    Integer insertInformation(Information information);

    void deleteInformation(Integer inid);

    Integer updateInformation(Information information);

    Information selectInformationById(Integer inid);
}
