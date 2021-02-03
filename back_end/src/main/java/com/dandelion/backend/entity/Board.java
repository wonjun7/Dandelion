package com.dandelion.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;


@Getter
@Setter
@Table(name = "board")
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @Column(name = "title", nullable = false)
    private String title;

    @JoinColumn(name = "member_number", nullable = false)
    private int memberNumber;

    @Column(name = "view", nullable = false)
    private int view;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @Column(name = "contents", length = 5000)
    private String contents;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "price", nullable = false)
    private int price;


    //     --FK--
    @OneToMany(mappedBy = "boardNumber", targetEntity = ItemImage.class)
    private List<ItemImage> itemImages = new ArrayList<>();

    @JsonManagedReference
    @OneToOne(mappedBy = "boardNumber", targetEntity = Locker.class)
    private Locker locker;

//    @JsonManagedReference
    @OneToOne(mappedBy = "boardNumber", targetEntity = Deal.class)
    private Deal deal;
}
