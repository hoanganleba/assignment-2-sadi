package com.example.assignment2sadi.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date date;

    @OneToOne
    @JoinColumn(name = "staff_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Staff staff;
}
