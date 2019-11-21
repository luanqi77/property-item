package com.qf.service.impl;

import com.qf.dao.AdviseRepository;
import com.qf.domain.Advise;
import com.qf.service.AdviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdviseServiceImpl implements AdviseService {
    @Autowired
    private AdviseRepository adviseRepository;
    @Override
    public String insertAdvise(Advise advise) {
        advise.setAdviseTime(new Date());
        Advise save = adviseRepository.save(advise);
        if (save!=null){
            return "ok";
        }
        return "fail";
    }

    @Override
    public List<Advise> selectAdviseByUserId(Integer userId) {
        if (userId!=null){
            List<Advise> adviseByUserId = adviseRepository.findAdviseByUserId(userId);
            return adviseByUserId;
        }
        return null;
    }

    @Override
    public Advise selectAdviseByAdviseId(Integer adviseId) {
        return adviseRepository.findAdviseByAdviseId(adviseId);
    }
}
