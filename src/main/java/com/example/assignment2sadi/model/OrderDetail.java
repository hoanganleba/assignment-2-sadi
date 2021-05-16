package com.example.assignment2sadi.model;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String product;

    @Column
    private int quantity;

    @Column
    private String price;
}
