package com.qf.service.impl;

import com.qf.dao.BoardResponsitory;
import com.qf.domain.Board;
import com.qf.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/13
 * @Time: 17:37
 */
@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardResponsitory boardResponsitory;

    @Override
    public List<Board> findAllBoard() {
        return boardResponsitory.findAll();
    }

    @Override
    public Board insertBoard(Board board) {
        if(board.getBoardDeso()!=""){
            Board board1 = boardResponsitory.save(board);
            return board1;
        }else {
            return null;
        }
    }

    @Override
    public void deleteBoard(Board board) {
        boardResponsitory.deleteById(board.getBoardId());
    }

    @Override
    public Board updateBoard(Board board) {
        return boardResponsitory.saveAndFlush(board);
    }

    @Override
    public Board selectBoardById(Integer boardId) {
        Optional<Board> board2 = boardResponsitory.findById(boardId);
        if (board2.isPresent()) {
            Board board = board2.get();
            return board;
        }
        return null;
    }
}
