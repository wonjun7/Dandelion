package com.dandelion.backend.entity.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DealStatus {

    ON_SALE("판매중"), //0
    AWAITING_STOCK("물건 등록 대기중"), //1
    CANCELED("판매 취소"), //2
    IN_STOCK("물건 등록 완료"), //3
    PICK_UP_COMPLETE("픽업 거래 완료"), //4
    BIRD_MATCHING("버드 픽업 대기"), //5
    BIRD_MATCHED("버드 배송중"), //6
    BIRD_COMPLETE("버드 거래완료"); //7

    private String value;
//    판매중                 -    게시글만 올린것 !                - 0
//    물건 등록 대기중        -    구매자가 구매한다고 한것!         - 1
//    판매 취소              -    판매자가 물건 안넣은것!           - 2
//
//    물건 등록 완료         -    판매자가 물건 넣은것!여기부터 12시간 카운트!!!          -  3
//
//    픽업 거래완료          -     픽업상태로 구매자가 가져간것!     - 4
//
//    버드 픽업 대기         -      구매자가 안가져가서 버드대기중   - 5
//
//    버드 배송중            -      버드가 매칭되서 배송중인것!     - 6
//
//    버드 거래완료          -      버드가 배달 완료한것 !          - 7
}
