package com.qf.service;

import com.qf.domain.Deduct;
import com.qf.domain.User;

import java.util.List;

public interface DeductService {
    Deduct getDeduct();
    String updateDeduct(Deduct deduct);
    List<User> warnJob();
    String payJob();
}
