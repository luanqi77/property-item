package com.qf.service.impl;

import com.qf.dao.StaffMapper;
import com.qf.domain.Staff;
import com.qf.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @PackageName:com.qf.service.impl;
 * @ClassName:StaffServiceImpl;
 * @author:马浩雲
 * @date2019/11/1416:38
 */
@Service
public class StaffServiceImpl implements StaffService {


    @Autowired
    private StaffMapper staffMapper;

    @Override
    public Staff selectStaffByStaffNumber(String staffNumber) {
        return staffMapper.selectStaffByStaffNumber(staffNumber);
    }
}
