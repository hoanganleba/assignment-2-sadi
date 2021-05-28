package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.SaleDetail;
import com.example.assignment2sadi.repository.SaleDetailRepository;
import com.example.assignment2sadi.repository.SaleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class SaleDetailController {

    private final SaleDetailRepository saleDetailRepository;
    private final SaleRepository saleRepository;

    public SaleDetailController(SaleDetailRepository saleDetailRepository, SaleRepository saleRepository) {
        this.saleDetailRepository = saleDetailRepository;
        this.saleRepository = saleRepository;
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

    @PostMapping("/sale/{saleId}/saleDetail")
    public Object createSaleDetail(@RequestBody SaleDetail saleDetail, @PathVariable Integer saleId) {
        return saleRepository.findById(saleId).map(sale -> {
            saleDetail.setSale(sale);
            return saleDetailRepository.save(saleDetail);
        });
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
