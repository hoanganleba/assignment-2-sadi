package com.example.assignment2sadi.repository;

import com.example.assignment2sadi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
