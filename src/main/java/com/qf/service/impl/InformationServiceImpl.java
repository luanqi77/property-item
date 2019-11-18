package com.qf.service.impl;

import com.qf.dao.InformationMapper;
import com.qf.domain.Information;
import com.qf.service.InformationService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/12
 * @Time: 11:58
 */
@Service
public class InformationServiceImpl implements InformationService {
    @Resource
    private InformationMapper informationMapper;

    @Override
    public List<Information> findAllInformation() {
        return informationMapper.findAllInformation();
    }

    @Override
    public List<Information> findAllInformationBypage(Integer page, Integer size) {
        Map<String, Object> data = new HashedMap();
        data.put("page",(page-1)*size);
        data.put("size",size);
        return informationMapper.findAllInformationBypage(data);
    }

    @Override
    public void deleteInformation(Integer inid) {
        informationMapper.deleteInformation(inid);
    }

    @Override
    public Integer insertInformation(Information information) {
        if (information.getDescription() != "") {
            Integer information2 = informationMapper.insertInformation(information);
            return information2;
        } else {
            return null;
        }
    }

    @Override
    public Integer updateInformation(Information information) {
        Integer information1 = informationMapper.updateInformation(information);
        return information1;
    }

    @Override
    public Information selectInformationById(Integer inid) {
        return informationMapper.selectInformationById(inid);
    }
}
