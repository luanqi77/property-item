package com.qf.service.impl;

import com.qf.dao.ApplyMapper;
import com.qf.dao.ApplyResponseitory;
import com.qf.domain.Apply;
import com.qf.domain.Applysss;
import com.qf.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName:com.qf.service.impl;
 * @ClassName:ApplyServiceImpl;
 * @author:马浩雲
 * @date2019/11/1411:11
 */
@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyResponseitory applyResponseitory;

    @Autowired
    private ApplyMapper applyMapper;


    @Override
    public Apply add(Apply apply) {
        return applyResponseitory.save(apply);
    }

    @Override
    public  List<Applysss> selectApplyByStaff(String status) {
       return applyMapper.selectApplyByStaff(status);
    }
}
