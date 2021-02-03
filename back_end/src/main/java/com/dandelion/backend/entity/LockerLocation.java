package com.dandelion.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "locker_location")
@Entity
public class LockerLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locNumber;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "locker_count")
    private int lockerCount;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "image", length = 360)
    private String image;


    @OneToMany(mappedBy = "locNumber", targetEntity = Locker.class)
    private List<Locker> lockers = new ArrayList<>();
}
