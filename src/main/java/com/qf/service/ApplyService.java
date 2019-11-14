package com.qf.service;

import com.qf.domain.Apply;
import com.qf.domain.Applysss;

import java.util.List;

public interface ApplyService {

    Apply add(Apply apply);

    List<Applysss> selectApplyByStaff(String status);
}

