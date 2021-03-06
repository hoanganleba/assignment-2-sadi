package com.example.assignment2sadi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "provider")
    @JsonIgnore
    private List<Order> order;
}
