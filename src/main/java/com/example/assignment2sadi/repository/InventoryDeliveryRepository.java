package com.example.assignment2sadi.repository;

import com.example.assignment2sadi.model.InventoryDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface InventoryDeliveryRepository extends JpaRepository<InventoryDelivery, Integer> {
    List<InventoryDelivery> findAllByDate(Date date);
    List<InventoryDelivery> findAllByDateBetween(Date startDate, Date endDate);
}
