package com.dandelion.backend.controller;

import com.dandelion.backend.entity.Board;
import com.dandelion.backend.entity.Deal;
import com.dandelion.backend.entity.Locker;
import com.dandelion.backend.entity.member.Member;
import com.dandelion.backend.entity.member.MyMemberDetails;
import com.dandelion.backend.repository.MemberRepository;
import com.dandelion.backend.service.BoardService;
import com.dandelion.backend.service.DealService;
import com.dandelion.backend.service.LockerService;
import com.dandelion.backend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class DealController {

    @Autowired
    DealService dealService;

    @Autowired
    BoardService boardService;

    @Autowired
    LockerService lockerService;

    @Autowired
    MemberRepository memberRepository;

    //deal 데이터 생성
    // 결제
    @RequestMapping(value = "/buy/{board_number}", method = RequestMethod.POST)
    public boolean awaitingStock(@PathVariable int board_number, @AuthenticationPrincipal MyMemberDetails myMemberDetails) {
        Deal deal = new Deal();
        Board board = boardService.getOne(board_number); //구매할 물건의 게시판 정보를 가져옴
        Member buyer = memberRepository.getOne(myMemberDetails.getNumber()); // 인터셉터로 member값을 가져와 적용
        Locker locker = lockerService.bringUnusedLocker(); // is_use = 0 인 locker를 찾아 1개의 locker의 정보를 가져옴

        locker.setBoardNumber(board); // locker 에 board 연결 (board_number 값을 넣음)
        locker.setIsUse(1); // locker is_use 값을 1로 변경 (locker 사용중으로 변경)
        board.setStatus(1); // board status 값을 1로 변경 (물품 등록 대기중으로 변경)
        deal.setBoardNumber(board);
        deal.setSellerNumber(board.getMemberNumber());
        deal.setBuyerNumber(buyer.getNumber());
        deal.setLockerNumber(locker.getNumber());
        deal.setIsPickup(0); // 처음엔 무조건 픽업으로 진행 IsPickup = 0 (추후에 버드 신청 가능)
        deal.setTempStatus(1); // status 1(물건등록 대기중)

        // 구매자의 잔액확인
        if (buyer.getPoint() >= board.getPrice() + 2000) {
            buyer.setPoint(buyer.getPoint() - board.getPrice() - 2000);
            memberRepository.save(buyer);
            lockerService.save(locker);
            boardService.save(board);
            dealService.save(deal);
            return true;
        } else {
            return false;
        }

//        {
//            "board_number": url 로 받은 보드넘버,
//            "seller_number": 보드넘버로 부터 판매자 정보 가져오기,
//            "buyer_number": 토큰값에서 판매자 정보 추출하기,
//            "bird_number": null (1 멤버가 null 멤버),
//            "locker_number": 비사용중인 라커에서 하나 가져오기 (1 -10) //비사용중인 라커 찾는 로직 필요
//            "create_date": new date(); entity 에서 기본값 들어가게 설정해둠...
//            "receiving_date": null
//            "shipping_date": null
//            "delivery_date": null
//            "is_pickup": 0 // null 처음에는 무조건 0 이후에 픽업을 신청하게 되면 1로 변경
//            "temp_status":  = 1 (물건 등록 대기중)
//        }

    }

    //물건 등록 완료
    @RequestMapping(value = "/inStock/{boardNumber}", method = RequestMethod.GET)
    public String inStock(@PathVariable int boardNumber) {
        Board board = boardService.findById(boardNumber).get();
        Deal deal = dealService.findDealWithBoard(board);
        Locker locker = board.getLocker();
        Member seller = memberRepository.getOne(deal.getSellerNumber());


        board.setStatus(3);
        deal.setTempStatus(3);
        deal.setReceivingDate(new Date());
        locker.setInputDate(new Date());
        seller.setPoint(seller.getPoint() + board.getPrice());

        boardService.save(board);
        dealService.save(deal);
        lockerService.save(locker);
        memberRepository.save(seller);

        return "물건 등록이 완료되었습니다";
    }

    //물건 거래 취소
    @RequestMapping(value = "/canceled/{boardNumber}", method = RequestMethod.GET)
    public String canceled(@PathVariable int boardNumber) {
        Board board = boardService.findById(boardNumber).get();
        Deal deal = dealService.findDealWithBoard(board);
        Locker locker = board.getLocker();
        Member buyer = memberRepository.getOne(deal.getBuyerNumber());

        board.setStatus(2);
        deal.setTempStatus(2);
        locker.setIsUse(0);
        locker.setBoardNumber(null);
        locker.setInputDate(null);
        buyer.setPoint(buyer.getPoint() + board.getPrice() + 2000);


        boardService.save(board);
        dealService.save(deal);
        lockerService.save(locker);
        memberRepository.save(buyer);

        return "item not received, deal cancel";
    }

    // 픽업 거래 완료
    @RequestMapping(value = "/pickUpComplete/{boardNumber}", method = RequestMethod.GET)
    public String pickUpComplete(@PathVariable int boardNumber) {
        Board board = boardService.findById(boardNumber).get();
        Deal deal = dealService.findDealWithBoard(board);
        Locker locker = board.getLocker();
        Member buyer = memberRepository.getOne(deal.getBuyerNumber());

        board.setStatus(4);
        deal.setTempStatus(4);
        deal.setIsPickup(1);
        deal.setShippingDate(new Date());
        locker.setIsUse(0);
        locker.setBoardNumber(null);
        locker.setInputDate(null);
        // 버드 수수료 반환
        buyer.setPoint(buyer.getPoint() + 2000);

        boardService.save(board);
        dealService.save(deal);
        lockerService.save(locker);
        memberRepository.save(buyer);

        return "item picked up";
    }

    //버드 픽업 대기
    @RequestMapping(value = "/birdMatching/{boardNumber}", method = RequestMethod.GET)
    public String birdMatching(@PathVariable int boardNumber) {
        Board board = boardService.findById(boardNumber).get();
        Deal deal = dealService.findDealWithBoard(board);

        board.setStatus(5);
        deal.setTempStatus(5);

        boardService.save(board);
        dealService.save(deal);

        return "bird matching start";
    }

    //버드 배송중
    @RequestMapping(value = "/birdMatched/{boardNumber}", method = RequestMethod.GET)
    public String birdMatched(@PathVariable int boardNumber, @AuthenticationPrincipal MyMemberDetails myMemberDetails) {
        Board board = boardService.findById(boardNumber).get();
        Deal deal = dealService.findDealWithBoard(board);
        Locker locker = board.getLocker();
        Member buyer = memberRepository.getOne(deal.getBuyerNumber());
        Member bird = memberRepository.getOne(myMemberDetails.getNumber());

        board.setStatus(6);
        deal.setTempStatus(6);
        deal.setShippingDate(new Date());
        locker.setIsUse(0);
        locker.setBoardNumber(null);
        locker.setInputDate(null);
        deal.setBirdNumber(bird.getNumber());

        boardService.save(board);
        dealService.save(deal);
        lockerService.save(locker);
        memberRepository.save(buyer);
        memberRepository.save(bird);

        return "bird matching complete";
    }

    //버드거래 완료
    @RequestMapping(value = "/birdComplete/{boardNumber}", method = RequestMethod.GET)
    public String birdComplete(@PathVariable int boardNumber) {
        Board board = boardService.findById(boardNumber).get();
        Deal deal = dealService.findDealWithBoard(board);
        Member bird = memberRepository.getOne(deal.getBirdNumber());

        board.setStatus(7);
        deal.setTempStatus(7);
        deal.setDeliveryDate(new Date());
        bird.setPoint(bird.getPoint() + 2000);

        boardService.save(board);
        dealService.save(deal);
        memberRepository.save(bird);

        return "bird delivery completed";
    }

            //내가 구매한 구매목록 찾기
    @RequestMapping(value = "/findMyBuy", method = RequestMethod.GET)
    public List<Deal> findMyBuy(@AuthenticationPrincipal MyMemberDetails myMemberDetails) {
        return dealService.findMyBuy(myMemberDetails.getNumber());
    }

    //판매자가 "등록완료버튼"을 클릭하면 receiving_date 값 변경
    @RequestMapping(value = "/receivingFinish/{board_number}", method = RequestMethod.GET)
    public Deal receivingFinish (@PathVariable int board_number) {
        return dealService.receivingFinish(board_number);
    }

    @GetMapping(value = "order-history/sell")
    public List<Deal> sell(@AuthenticationPrincipal MyMemberDetails myMemberDetails){
        Member member = myMemberDetails.getMember();
        return dealService.findBySeller(member.getNumber());
    }

    @GetMapping(value = "order-history/buy")
    public List<Deal> buy(@AuthenticationPrincipal MyMemberDetails myMemberDetails){
        Member member = myMemberDetails.getMember();

        return dealService.findByBuyer(member.getNumber());
    }
}
