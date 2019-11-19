package com.qf.service.impl;

import com.qf.dao.AdviseRepository;
import com.qf.domain.Advise;
import com.qf.service.AdviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public Advise selectAdviseByadviseId(Integer adviseId) {
        if (adviseId!=null){
            Advise adviseByAdviseId = adviseRepository.findAdviseByAdviseId(adviseId);
            return adviseByAdviseId;
        }
        return null;
    }
}
