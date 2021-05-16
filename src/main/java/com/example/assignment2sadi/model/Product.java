package com.example.assignment2sadi.model;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String model;

    @Column
    private String brand;

    @Column
    private String company;

    @Column
    private String description;

    @Column
    private String category;

    @Column
    private String price;
}
