package com.example.assignment2sadi.repository;

import com.example.assignment2sadi.model.InventoryReceive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface InventoryReceiveRepository extends JpaRepository<InventoryReceive, Integer> {
    List<InventoryReceive> findAllByDate(Date date);
    List<InventoryReceive> findAllByDateBetween(Date startDate, Date endDate);
}
