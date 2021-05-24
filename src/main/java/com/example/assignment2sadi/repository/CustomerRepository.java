package com.example.assignment2sadi.repository;

import com.example.assignment2sadi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByName(String customerName);
}
