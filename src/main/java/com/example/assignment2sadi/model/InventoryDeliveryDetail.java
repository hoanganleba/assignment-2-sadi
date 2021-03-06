package com.example.assignment2sadi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class InventoryDeliveryDetail {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String product;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "inventoryDelivery_id")
    @JsonIgnore
    private InventoryDelivery inventoryDelivery;
}
