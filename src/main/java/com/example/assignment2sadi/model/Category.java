package com.example.assignment2sadi.model;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
}
