package com.qf.service.impl;

import com.qf.dao.BoardResponsitory;
import com.qf.domain.Advise;
import com.qf.domain.Board;
import com.qf.domain.Information;
import com.qf.response.ResponseUser;
import com.qf.service.BoardService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

//    @Override
//    public ResponseUser findAllBoardBypage(Integer page, Integer size) {
//        Map<String, Object> data = new HashedMap();
//        data.put("page",(page-1)*size);
//        data.put("size",size);
//        List<Board> allBoardBypage = boardMapper.findAllBoardBypage(data);
//        ResponseUser user = new ResponseUser();
//        user.setList(allBoardBypage);
//        long bytotal1 = boardMapper.selectBoardBytotal();
//        user.setTotal(bytotal1);
//        return user;
//    }
@Override
public ResponseUser findAllBoardBypage(Integer page, Integer size) {
    PageRequest of = PageRequest.of(page - 1, size);
    Page<Board> all = boardResponsitory.findAll(of);
    List<Board> content = all.getContent();
    long totalElements = all.getTotalElements();
    ResponseUser responseUser = new ResponseUser();
    responseUser.setTotal(totalElements);
    responseUser.setList(content);
    return responseUser;
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
