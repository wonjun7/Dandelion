package com.dandelion.backend.controller;


import com.dandelion.backend.entity.Board;
import com.dandelion.backend.entity.Deal;
import com.dandelion.backend.entity.member.MyMemberDetails;
import com.dandelion.backend.repository.ImageRepository;
import com.dandelion.backend.service.BoardService;
import com.dandelion.backend.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")
@RestController
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    DealService dealService;

    @RequestMapping(value = "/addBoard", method = RequestMethod.POST)
    public Board save(@RequestBody Board board, @AuthenticationPrincipal MyMemberDetails myMemberDetails) {
        board.setMemberNumber(myMemberDetails.getNumber());
        return boardService.save(board);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Stream<Board> boardList() {
        return boardService.findAll().stream()
                .sorted(Comparator.comparing(Board::getNumber).reversed());
    }

    @RequestMapping(value = "/findBoardId/{id}", method = RequestMethod.GET)
    public Optional<Board> findId(@PathVariable int id) {
        return boardService.findById(id);
    }

    @RequestMapping(value = "/modifyBoard/{id}", method = RequestMethod.POST)
    public Board modifyBoard(@PathVariable int id, @RequestBody Board newBoard) {
        return boardService.modifyBoard(id, newBoard);
    }

    @RequestMapping(value = "/board/{partOfTitle}", method = RequestMethod.GET)
    public List<Board> searchWithTitle(@PathVariable String partOfTitle) {
        return boardService.searchWithTitle(partOfTitle);
    }


    //내가 등록한 게시판 찾기
    @RequestMapping(value = "/board/findMyBoard", method = RequestMethod.GET)
    public List<Board> findMyBoard(@AuthenticationPrincipal MyMemberDetails myMemberDetails) {
        return boardService.findMyBoard(myMemberDetails.getNumber());
    }

    //내가 구매한 게사판 찾기
    @RequestMapping(value = "/board/findMyBuy", method = RequestMethod.GET)
    public List<Board> findMyBuy(@AuthenticationPrincipal MyMemberDetails myMemberDetails) {
        List<Board> buyBoardList = new ArrayList<>();

        List<Deal> buyList = dealService.findMyBuy(myMemberDetails.getNumber());

        buyList.stream()
                .forEach(deal -> buyBoardList.add(boardService.getOne(1)));

//        for (Deal deal : buyList) {
//            buyBoardList.add(boardService.getOne(deal.getBoardNumber()));
//        }

        return buyBoardList;
    }

    //구매자가 "구매하기" 버튼을 누르면 board_status 값은 1(물건등록중) 으로 변경
    @RequestMapping(value = "/board/deal/buytStatus/{board_number}", method = RequestMethod.GET)
    public Board buy(@PathVariable int board_number) {
        Board board = boardService.getOne(board_number);
        board.setStatus(1);
        return boardService.save(board);
    }





}
