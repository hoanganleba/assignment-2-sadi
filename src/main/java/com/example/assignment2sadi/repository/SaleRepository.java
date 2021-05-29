package com.example.assignment2sadi.repository;

import com.example.assignment2sadi.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findAllByDate(Date date);
    List<Sale> findAllByDateBetween(Date startDate, Date endDate);
}
