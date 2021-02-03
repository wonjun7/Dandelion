package com.dandelion.backend.repository;

import com.dandelion.backend.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Integer> {

    @Query(value = "SELECT * FROM deal d where d.buyer_number = :number", nativeQuery = true)
    List<Deal> findMyBuy(@Param("number") int number);

    @Query(value = "SELECT * from deal d where d.board_number = :number", nativeQuery = true)
    Deal findByBoardNumber(@Param("number")int number);

    List<Deal> findBysellerNumber(int sellerNumber);

    List<Deal> findBybuyerNumber(int buyerNumber);

}
