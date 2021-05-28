package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.SaleDetail;
import com.example.assignment2sadi.repository.SaleDetailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class SaleDetailController {

    private final SaleDetailRepository saleDetailRepository;

    public SaleDetailController(SaleDetailRepository saleDetailRepository) {
        this.saleDetailRepository = saleDetailRepository;
    }

    @GetMapping("/saleDetails")
    public List<SaleDetail> getSaleDetails() {
        return saleDetailRepository.findAll();
    }

    // Get sale by sale id
    @GetMapping("/saleDetail/{saleDetailId}")
    public Object getSaleDetailById(@PathVariable Integer saleDetailId) {
        return saleDetailRepository.findById(saleDetailId);
    }

    // Add sale
    @PostMapping("/saleDetail")
    public Object createSaleDetail(@RequestBody SaleDetail saleDetail) {
        return saleDetailRepository.save(saleDetail);
    }

    // Delete
    @DeleteMapping("/saleDetail/{saleDetailId}")
    public void deleteSaleDetail(@PathVariable Integer saleDetailId) {
        saleDetailRepository.findById(saleDetailId).map(saleDetail -> {
            saleDetailRepository.delete(saleDetail);
            return ResponseEntity.ok().build();
        });
    }
}
