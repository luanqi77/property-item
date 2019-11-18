package com.qf.service;

import com.qf.domain.Information;
import com.qf.response.ResponseUser;

import java.util.List;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/12
 * @Time: 11:54
 */
public interface InformationService {
    List<Information> findAllInformation();

    ResponseUser findAllInformationBypage(Integer page, Integer size);

    void deleteInformation(Integer inid);

    Integer insertInformation(Information information);

    Integer updateInformation(Information information);

    Information selectInformationById(Integer inid);

}
