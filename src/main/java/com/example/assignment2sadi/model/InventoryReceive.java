package com.example.assignment2sadi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class InventoryReceive {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date date;

    @Column
    private String name;

    @OneToMany(mappedBy = "inventoryReceive", cascade = CascadeType.ALL)
    private List<InventoryReceiveDetail> inventoryReceiveDetails;
}