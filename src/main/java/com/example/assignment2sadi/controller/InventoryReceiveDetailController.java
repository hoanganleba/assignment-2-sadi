package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.InventoryReceiveDetail;
import com.example.assignment2sadi.repository.InventoryReceiveDetailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class InventoryReceiveDetailController {

    private final InventoryReceiveDetailRepository inventoryReceiveDetailRepository;

    public InventoryReceiveDetailController(InventoryReceiveDetailRepository inventoryReceiveDetailRepository) {
        this.inventoryReceiveDetailRepository = inventoryReceiveDetailRepository;
    }

    @GetMapping("/inventoryReceiveDetails")
    public List<InventoryReceiveDetail> getInventoryReceiveDetails() {
        return inventoryReceiveDetailRepository.findAll();
    }

    // Get inventoryReceiveDetail by inventoryReceiveDetail id
    @GetMapping("/inventoryReceiveDetail/{inventoryReceiveDetailId}")
    public Object getInventoryReceiveDetailById(@PathVariable Integer inventoryReceiveDetailId) {
        return inventoryReceiveDetailRepository.findById(inventoryReceiveDetailId);
    }

    // Add inventoryReceiveDetail
    @PostMapping("/inventoryReceiveDetail")
    public Object createInventoryReceiveDetail(@RequestBody InventoryReceiveDetail inventoryReceiveDetail) {
        return inventoryReceiveDetailRepository.save(inventoryReceiveDetail);
    }

    // Update
    @PutMapping("/inventoryReceiveDetail/{inventoryReceiveDetailId}")
    public Object updateInventoryReceiveDetail(@RequestBody InventoryReceiveDetail newInventoryReceiveDetail, @PathVariable Integer inventoryReceiveDetailId) {
        return inventoryReceiveDetailRepository.findById(inventoryReceiveDetailId).map(inventoryReceiveDetail -> {
            inventoryReceiveDetailRepository.save(inventoryReceiveDetail);
            return ResponseEntity.ok(newInventoryReceiveDetail);
        });
    }

    // Delete
    @DeleteMapping("/inventoryReceiveDetail/{inventoryReceiveDetailId}")
    public void deleteInventoryReceiveDetail(@PathVariable Integer inventoryReceiveDetailId) {
        inventoryReceiveDetailRepository.findById(inventoryReceiveDetailId).map(inventoryReceiveDetail -> {
            inventoryReceiveDetailRepository.delete(inventoryReceiveDetail);
            return ResponseEntity.ok().build();
        });
    }
}
