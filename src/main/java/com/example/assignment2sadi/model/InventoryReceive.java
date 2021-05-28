package com.example.assignment2sadi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="UTC")
    private Date date;

    @Column
    private String name;

    @OneToMany(mappedBy = "inventoryReceive", cascade = CascadeType.ALL)
    private List<InventoryReceiveDetail> inventoryReceiveDetails;
}
