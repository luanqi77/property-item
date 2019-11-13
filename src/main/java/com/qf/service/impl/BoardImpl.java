package com.qf.service.impl;

import com.qf.dao.BoardRespority;
import com.qf.domain.Board;
import com.qf.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Company: 憨憨互联
 * @Author: 张朝
 * @Date: 2019/11/12
 * @Time: 14:52
 */
@Service
public class BoardImpl implements BoardService{
    @Autowired
    BoardRespority boardRespority;

    @Override
    public List<Board> findAll() {
        return boardRespority.findAll();
    }

    @Override
    public Board create(Board board) {
        Board save = boardRespority.save(board);
        return save;
    }

    @Override
    public void deleteById(Integer boardId) {
        boardRespority.deleteById(boardId);
    }

    @Override
    public Board update(Board board) {
        Board save = boardRespority.save(board);
        return save;
    }
}
