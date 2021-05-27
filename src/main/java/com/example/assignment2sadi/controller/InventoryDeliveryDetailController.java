package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.InventoryDeliveryDetail;
import com.example.assignment2sadi.repository.InventoryDeliveryDetailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class InventoryDeliveryDetailController {

    private final InventoryDeliveryDetailRepository inventoryDeliveryDetailRepository;

    public InventoryDeliveryDetailController(InventoryDeliveryDetailRepository inventoryDeliveryDetailRepository) {
        this.inventoryDeliveryDetailRepository = inventoryDeliveryDetailRepository;
    }

    @GetMapping("/inventoryDeliveryDetails")
    public List<InventoryDeliveryDetail> getInventoryDeliveryDetails() {
        return inventoryDeliveryDetailRepository.findAll();
    }

    // Get inventoryDeliveryDetail by inventoryDeliveryDetail id
    @GetMapping("/inventoryDeliveryDetail/{inventoryDeliveryDetailId}")
    public Object getInventoryDeliveryDetailById(@PathVariable Integer inventoryDeliveryDetailId) {
        return inventoryDeliveryDetailRepository.findById(inventoryDeliveryDetailId);
    }

    // Add inventoryDeliveryDetail
    @PostMapping("/inventoryDeliveryDetail")
    public Object createInventoryDeliveryDetail(@RequestBody InventoryDeliveryDetail inventoryDeliveryDetail) {
        return inventoryDeliveryDetailRepository.save(inventoryDeliveryDetail);
    }

    // Update
    @PutMapping("/inventoryDeliveryDetail/{inventoryDeliveryDetailId}")
    public Object updateInventoryDeliveryDetail(@RequestBody InventoryDeliveryDetail newInventoryDeliveryDetail, @PathVariable Integer inventoryDeliveryDetailId) {
        return inventoryDeliveryDetailRepository.findById(inventoryDeliveryDetailId).map(inventoryDeliveryDetail -> {
            inventoryDeliveryDetailRepository.save(inventoryDeliveryDetail);
            return ResponseEntity.ok(newInventoryDeliveryDetail);
        });
    }

    // Delete
    @DeleteMapping("/inventoryDeliveryDetail/{inventoryDeliveryDetailId}")
    public void deleteInventoryDeliveryDetail(@PathVariable Integer inventoryDeliveryDetailId) {
        inventoryDeliveryDetailRepository.findById(inventoryDeliveryDetailId).map(inventoryDeliveryDetail -> {
            inventoryDeliveryDetailRepository.delete(inventoryDeliveryDetail);
            return ResponseEntity.ok().build();
        });
    }
}
