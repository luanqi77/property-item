package com.qf.dao;

import com.qf.domain.AdviseAndReply;
import com.qf.domain.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
    int deleteByPrimaryKey(Integer replyId);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer replyId);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);

    List<AdviseAndReply> selectAdviseAndReplyByStaff(@Param("startIndex") Integer startIndex,
                     @Param("pageSize") Integer  pageSize, @Param("staffId") Integer staffId);

    Integer selectReplyByStaffId(Integer staffId);
}