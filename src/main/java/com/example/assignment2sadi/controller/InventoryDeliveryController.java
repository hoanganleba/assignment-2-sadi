package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.InventoryDelivery;
import com.example.assignment2sadi.repository.InventoryDeliveryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class InventoryDeliveryController {

    private final InventoryDeliveryRepository inventoryDeliveryRepository;

    public InventoryDeliveryController(InventoryDeliveryRepository inventoryDeliveryRepository) {
        this.inventoryDeliveryRepository = inventoryDeliveryRepository;
    }

    @GetMapping("/inventoryDeliveries")
    public List<InventoryDelivery> getInventoryDeliveries() {
        return inventoryDeliveryRepository.findAll();
    }

    // Get inventoryDelivery by inventoryDelivery id
    @GetMapping("/inventoryDelivery/{inventoryDeliveryId}")
    public Object getInventoryDeliveryById(@PathVariable Integer inventoryDeliveryId) {
        return inventoryDeliveryRepository.findById(inventoryDeliveryId);
    }

    // Add inventoryDelivery
    @PostMapping("/inventoryDelivery")
    public Object createInventoryDelivery(@RequestBody InventoryDelivery inventoryDelivery) {
        return inventoryDeliveryRepository.save(inventoryDelivery);
    }

    // Update
    @PutMapping("/inventoryDelivery/{inventoryDeliveryId}")
    public Object updateInventoryDelivery(@RequestBody InventoryDelivery newInventoryDelivery, @PathVariable Integer inventoryDeliveryId) {
        return inventoryDeliveryRepository.findById(inventoryDeliveryId).map(inventoryDelivery -> {
            inventoryDelivery.setName(newInventoryDelivery.getName());
            inventoryDeliveryRepository.save(inventoryDelivery);
            return ResponseEntity.ok(newInventoryDelivery);
        });
    }

    // Delete
    @DeleteMapping("/inventoryDelivery/{inventoryDeliveryId}")
    public void deleteInventoryDelivery(@PathVariable Integer inventoryDeliveryId) {
        inventoryDeliveryRepository.findById(inventoryDeliveryId).map(inventoryDelivery -> {
            inventoryDeliveryRepository.delete(inventoryDelivery);
            return ResponseEntity.ok().build();
        });
    }
}
