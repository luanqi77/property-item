package com.qf.service;

import com.qf.domain.Advise;

import java.util.List;

public interface AdviseService {
    String insertAdvise(Advise advise);

    List<Advise> selectAdviseByUserId(Integer userId);

    Advise selectAdviseByAdviseId(Integer adviseId);
}
