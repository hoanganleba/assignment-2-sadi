package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.Sale;
import com.example.assignment2sadi.repository.SaleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class SaleController {

    private final SaleRepository saleRepository;

    public SaleController(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @GetMapping("/sales")
    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    // Get sale by sale id
    @GetMapping("/sale/{saleId}")
    public Object getSaleById(@PathVariable Integer saleId) {
        return saleRepository.findById(saleId);
    }

    // Add sale
    @PostMapping("/sale")
    public Object createSale(@RequestBody Sale sale) {
        return saleRepository.save(sale);
    }

    // Delete
    @DeleteMapping("/sale/{saleId}")
    public void deleteSale(@PathVariable Integer saleId) {
        saleRepository.findById(saleId).map(sale -> {
            saleRepository.delete(sale);
            return ResponseEntity.ok().build();
        });
    }
}
