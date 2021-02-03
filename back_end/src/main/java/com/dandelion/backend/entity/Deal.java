package com.dandelion.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Table(name = "deal")
@Entity
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

//    @JsonBackReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    @OneToOne
    @JoinColumn(name = "board_number", nullable = false)
    private Board boardNumber;

    @JoinColumn(name = "seller_number", nullable = false)
    private int sellerNumber;

    @JoinColumn(name = "buyer_number")
    private int buyerNumber;

    @JoinColumn(name = "bird_number", nullable = true)
    private Integer birdNumber;

    @JoinColumn(name = "locker_number")
    private int lockerNumber;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();

    @Column(name = "receiving_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivingDate;

    @Column(name = "shipping_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shippingDate;

    @Column(name = "delivery_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    @Column(name = "is_pickup", nullable = false)
    private int isPickup;

    @Column(name = "temp_status", nullable = false)
    private int tempStatus;

}

