package com.qf.dao;

import com.qf.domain.Advise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdviseRepository extends JpaRepository<Advise,Integer> {

    List<Advise> findAdviseByUserId(Integer userId);
}


