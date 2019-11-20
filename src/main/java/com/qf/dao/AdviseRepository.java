package com.qf.dao;

import com.qf.domain.Advise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdviseRepository extends JpaRepository<Advise,Integer> {

    Advise findAdviseByAdviseId(Integer adviseId);
}


