package com.qf.service.impl;

import com.qf.dao.AdviseRepository;
import com.qf.domain.Advise;
import com.qf.service.AdviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdviseServiceImpl implements AdviseService {
    @Autowired
    private AdviseRepository adviseRepository;
    @Override
    public String insertAdvise(Advise advise) {
        Advise save = adviseRepository.save(advise);
        if (save!=null){
            return "ok";
        }
        return "fail";
    }
}
