package com.qf.service;

import com.qf.bean.PageBeanFindAdviseAndReply;
import com.qf.domain.AdviseAndReply;
import com.qf.domain.Reply;
import com.qf.response.ResponseUser;

import java.util.List;

public interface ReplyService {
    ResponseUser adviseFindAll(Integer page,Integer size);

    String insertReply(Reply reply);

    PageBeanFindAdviseAndReply selectReplyByStaffId(Integer size,Integer page,Integer staffId);
}
