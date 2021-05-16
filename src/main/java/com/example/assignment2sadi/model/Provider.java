package com.example.assignment2sadi.model;

import javax.persistence.*;

@Entity
public class Provider {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @Column
    private String fax;

    @Column
    private String email;

    @Column
    private String contactPerson;
}
