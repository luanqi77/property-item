package com.qf.service.impl;

import com.qf.dao.ApplyMapper;
import com.qf.dao.ApplyResponseitory;
import com.qf.domain.Apply;
import com.qf.domain.ApplyByPageAndSize;
import com.qf.domain.Applysss;
import com.qf.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Applysss> selectApplyByStaff(Integer status) {

        return applyMapper.selectApplyByStaff(status);
    }

    @Override
    public Apply selectApplyByApplyId(Integer applyId) {
        Optional<Apply> byId = applyResponseitory.findById(applyId);
        if (byId.isPresent()) {
            Apply apply = byId.get();
            return apply;
        }
        return null;
    }

    @Override
    public Apply updateApplyByStatus(Apply apply) {
        return applyResponseitory.saveAndFlush(apply);
    }

    @Override
    public ApplyByPageAndSize ApplyFindAll(Integer status, Integer page, Integer size) {
        Integer startIndex=(page-1)*size;
        List<Applysss> applies = applyMapper.ApplyFindAll(startIndex, page, size);
        Integer total = applyMapper.selectApplyByApplyStatus(status);
        ApplyByPageAndSize applyByPageAndSize =new ApplyByPageAndSize();
        applyByPageAndSize.setApplyList(applies);
        applyByPageAndSize.setTotal(total);

        return applyByPageAndSize;
    }
}
