package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.InventoryDelivery;
import com.example.assignment2sadi.model.InventoryReceive;
import com.example.assignment2sadi.repository.InventoryDeliveryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @GetMapping("/inventoryDeliveriesByDate")
    public List<InventoryDelivery> getInventoryReceives(@RequestParam String date) throws ParseException {
        return inventoryDeliveryRepository.findAllByDate(
                new SimpleDateFormat("yyyy-MM-dd").parse(date)
        );
    }

    @GetMapping("/inventoryDeliveriesByPeriod")
    public List<InventoryDelivery> getInventoryDeliveries(@RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        return inventoryDeliveryRepository.findAllByDateBetween(
                new SimpleDateFormat("yyyy-MM-dd").parse(startDate),
                new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
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
