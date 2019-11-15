package com.qf.service.impl;

import com.qf.dao.AdviseMapper;
import com.qf.dao.AdviseRepository;
import com.qf.dao.ReplyRepository;
import com.qf.domain.Advise;
import com.qf.domain.Reply;
import com.qf.response.ResponseUser;
import com.qf.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyRepository replyRepository;
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
        Reply save = replyRepository.save(reply);
        Advise advise = new Advise();
        advise.setAdviseId(reply.getAdviseId());
        advise.setStatus(1);
        adviseMapper.updateStatusByAdviseId(advise);
        return "success";
    }
}
