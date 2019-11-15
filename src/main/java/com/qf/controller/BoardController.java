package com.qf.controller;

import com.qf.domain.Board;
import com.qf.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/13
 * @Time: 17:52
 */
@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping("/findAllBoard")
    public List<Board> findAllBoard(){
        return boardService.findAllBoard();
    }

    @RequestMapping("/insertBoard")
    public Board insertBoard(@RequestBody Board board){
        return boardService.insertBoard(board);
    }

    @RequestMapping("/deleteBoard")
    public void deleteBoard(@RequestBody Board board){
        boardService.deleteBoard(board);
    }

    @RequestMapping("/updateBoard")
    public Board updateBoard(@RequestBody Board board){
        return boardService.updateBoard(board);
    }

    @RequestMapping("/selectBoardById")
    public Board selectBoardById(@RequestBody Board board){
        Board board1 = boardService.selectBoardById(board.getBoardId());
        return board1;

    }
}
