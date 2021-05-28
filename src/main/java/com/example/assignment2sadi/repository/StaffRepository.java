package com.example.assignment2sadi.repository;

import com.example.assignment2sadi.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

}
