package com.example.assignment2sadi.repository;

import com.example.assignment2sadi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
