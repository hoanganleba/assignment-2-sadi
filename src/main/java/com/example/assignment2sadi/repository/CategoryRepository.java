package com.example.assignment2sadi.repository;

import com.example.assignment2sadi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
