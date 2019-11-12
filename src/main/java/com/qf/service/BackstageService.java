package com.qf.service;

import com.qf.domain.Staff;

public interface BackstageService {
    String login(Staff staff);
    String updatePassword(Staff staff);
}
