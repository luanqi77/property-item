package com.qf.service;

import com.qf.domain.Board;

import java.util.List;

/**
 * @Description:
 * @Company: 憨憨互联
 * @Author: 张朝
 * @Date: 2019/11/12
 * @Time: 14:49
 */

public interface BoardService {
    public List<Board> findAll();

    public Board create(Board board);

    public void deleteById(Integer boardId);

    public Board update(Board board);
}
