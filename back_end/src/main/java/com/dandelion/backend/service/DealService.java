package com.dandelion.backend.service;

import com.dandelion.backend.entity.Board;
import com.dandelion.backend.entity.Deal;
import com.dandelion.backend.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Service
public class DealService {

    @Autowired
    DealRepository dealRepository;

    public Deal save (Deal deal) {
        return dealRepository.save(deal);
    }

    public List<Deal> findMyBuy (int number) {
        return dealRepository.findMyBuy(number);
    }

    public Deal receivingFinish (int board_number) {
        Deal deal = dealRepository.findByBoardNumber(board_number);
        deal.setReceivingDate(new Date());
        return dealRepository.save(deal);
    }

    public List<Deal> findBySeller(int sellerNumber){
        return dealRepository.findBysellerNumber(sellerNumber);
    }

    public List<Deal> findByBuyer(int buyerNumber){
        return dealRepository.findBybuyerNumber(buyerNumber);
    }

    public Deal findDealWithBoard(Board board) {
        return dealRepository.findByBoardNumber(board.getNumber());
    }
}
