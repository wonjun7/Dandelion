package com.dandelion.backend.entity.member;


import com.dandelion.backend.entity.Board;
import com.dandelion.backend.entity.Deal;
import com.dandelion.backend.entity.role.MemberRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "member")
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @Column(name = "id", nullable = false, unique = true, length = 50)
    private String id;

    @Column(name = "password", nullable = false, length = 100)
    private String pw;

    @Column(name = "name", nullable = false, length = 50)
    private String name = "name";

    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Column(name = "address", nullable = false, length = 150)
    private String address = "seoul";

    @Column(name = "is_bird", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private MemberRole isBird;

    @Column(name = "point", nullable = false)
    private int point;


    @OneToMany(mappedBy = "memberNumber", targetEntity = Board.class)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "sellerNumber", targetEntity = Deal.class)
    private List<Deal> sellDeals = new ArrayList<>();

    @OneToMany(mappedBy = "buyerNumber", targetEntity = Deal.class)
    private List<Deal> buyDeals = new ArrayList<>();

    @OneToMany(mappedBy = "birdNumber", targetEntity = Deal.class)
    private List<Deal> birdDeals = new ArrayList<>();
}
