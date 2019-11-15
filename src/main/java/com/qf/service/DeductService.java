package com.qf.service;

import com.qf.domain.Deduct;


public interface DeductService {
    Deduct getDeduct();
    String updateDeduct(Deduct deduct);
    String warnJob();
    String payJob();
}
