package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.InventoryDelivery;
import com.example.assignment2sadi.model.InventoryReceive;
import com.example.assignment2sadi.model.Sale;
import com.example.assignment2sadi.repository.InventoryReceiveRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class InventoryReceiveController {

    private final InventoryReceiveRepository inventoryReceiveRepository;

    public InventoryReceiveController(InventoryReceiveRepository inventoryReceiveRepository) {
        this.inventoryReceiveRepository = inventoryReceiveRepository;
    }

    @GetMapping("/inventoryReceives")
    public List<InventoryReceive> getInventoryReceives() {
        return inventoryReceiveRepository.findAll();
    }

    @GetMapping("/inventoryReceivesByDate")
    public List<InventoryReceive> getInventoryReceives(@RequestParam String date) throws ParseException {
        return inventoryReceiveRepository.findAllByDate(
                new SimpleDateFormat("yyyy-MM-dd").parse(date)
        );
    }

    @GetMapping("/inventoryReceivesByPeriod")
    public List<InventoryReceive> getInventoryDeliveries(@RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        return inventoryReceiveRepository.findAllByDateBetween(
                new SimpleDateFormat("yyyy-MM-dd").parse(startDate),
                new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
    }

    // Get inventoryReceive by inventoryReceive id
    @GetMapping("/inventoryReceive/{inventoryReceiveId}")
    public Object getInventoryReceiveById(@PathVariable Integer inventoryReceiveId) {
        return inventoryReceiveRepository.findById(inventoryReceiveId);
    }

    // Add inventoryReceive
    @PostMapping("/inventoryReceive")
    public Object createInventoryReceive(@RequestBody InventoryReceive inventoryReceive) {
        return inventoryReceiveRepository.save(inventoryReceive);
    }

    // Update
    @PutMapping("/inventoryReceive/{inventoryReceiveId}")
    public Object updateInventoryReceive(@RequestBody InventoryReceive newInventoryReceive, @PathVariable Integer inventoryReceiveId) {
        return inventoryReceiveRepository.findById(inventoryReceiveId).map(inventoryReceive -> {
            inventoryReceive.setName(newInventoryReceive.getName());
            inventoryReceive.setDate(newInventoryReceive.getDate());
            inventoryReceiveRepository.save(inventoryReceive);
            return ResponseEntity.ok(newInventoryReceive);
        });
    }

    // Delete
    @DeleteMapping("/inventoryReceive/{inventoryReceiveId}")
    public void deleteInventoryReceive(@PathVariable Integer inventoryReceiveId) {
        inventoryReceiveRepository.findById(inventoryReceiveId).map(inventoryReceive -> {
            inventoryReceiveRepository.delete(inventoryReceive);
            return ResponseEntity.ok().build();
        });
    }


}
