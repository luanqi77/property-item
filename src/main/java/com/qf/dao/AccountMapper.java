package com.qf.dao;

import com.qf.bean.UserAccount;
import com.qf.domain.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    List<UserAccount> findUserAccount();

    Integer payFee();

    int deleteByUserId(Integer userId);

    Integer save(Integer userId);

    Double selectMoneyByUserId(Integer userId);
}