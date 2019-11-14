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
    public List<Board> findAllBoard();

    public Board insertBoard(Board board);

    public void deleteBoard(Board board);

    public Board updateBoard(Board board);

    public Board selectBoardById(Integer boardId);
}
