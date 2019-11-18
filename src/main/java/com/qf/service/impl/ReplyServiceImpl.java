package com.qf.service.impl;

import com.qf.bean.PageBeanFindAdviseAndReply;
import com.qf.dao.AdviseMapper;
import com.qf.dao.AdviseRepository;
import com.qf.dao.ReplyMapper;
import com.qf.dao.ReplyRepository;
import com.qf.domain.Advise;
import com.qf.domain.AdviseAndReply;
import com.qf.domain.Reply;
import com.qf.response.ResponseUser;
import com.qf.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private AdviseRepository adviseRepository;
    @Autowired
    private AdviseMapper adviseMapper;


    @Override
    public ResponseUser adviseFindAll(Integer page, Integer size) {
        PageRequest of = PageRequest.of(page - 1, size);
        Page<Advise> all = adviseRepository.findAll(of);

        List<Advise> content = all.getContent();
        long totalElements = all.getTotalElements();
        ResponseUser responseUser = new ResponseUser();
        responseUser.setTotal(totalElements);
        responseUser.setList(content);
        return responseUser;
    }

    @Override
    public String insertReply(Reply reply) {
        reply.setReplyTime(new Date());
        Reply save = replyRepository.save(reply);
        System.out.println(save);
        Advise advise = new Advise();
        advise.setAdviseId(reply.getAdviseId());
        advise.setStatus("已回复");
        adviseMapper.updateStatusByAdviseId(advise);
        return "success";
    }

    @Override
    public PageBeanFindAdviseAndReply selectReplyByStaffId(Integer page,Integer size,Integer staffId) {
        Integer startIndex=(page-1)*size;
        List<AdviseAndReply> adviseAndReplies = replyMapper.selectAdviseAndReplyByStaff(startIndex, size, staffId);
        Integer total=replyMapper.selectReplyByStaffId(staffId);
        PageBeanFindAdviseAndReply pageBean=new PageBeanFindAdviseAndReply();
        pageBean.setList(adviseAndReplies);
        pageBean.setTotalCount(total);
        return pageBean;

    }

    @Override
    public List<Reply> selectReplyByAdviseId(Integer adviseId) {
        return replyRepository.findReplyByAdviseId(adviseId);
    }
}
