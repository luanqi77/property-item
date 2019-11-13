package com.qf.dao;

import com.qf.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @Company: 憨憨互联
 * @Author: 张朝
 * @Date: 2019/11/12
 * @Time: 11:51
 */
public interface BoardRespority extends JpaRepository<Board,Integer>{

}
