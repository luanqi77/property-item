package com.qf.service;

import com.qf.domain.Reply;
import com.qf.response.ResponseUser;

public interface ReplyService {
    ResponseUser adviseFindAll(Integer page,Integer size);

    String insertReply(Reply reply);
}
