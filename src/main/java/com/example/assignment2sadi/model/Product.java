package com.example.assignment2sadi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private String price;
}
