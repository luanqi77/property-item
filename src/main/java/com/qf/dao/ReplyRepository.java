package com.qf.dao;

import com.qf.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Integer> {

    List<Reply> findReplyByAdviseId(Integer adviseId);
}
