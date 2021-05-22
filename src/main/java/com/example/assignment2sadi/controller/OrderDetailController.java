package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.OrderDetail;
import com.example.assignment2sadi.repository.OrderDetailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class OrderDetailController {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailController(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @GetMapping("/orderDetails")
    public List<OrderDetail> getOrderDetails() {
        return orderDetailRepository.findAll();
    }

    // Get orderDetail by orderDetail id
    @GetMapping("/orderDetail/{orderDetailId}")
    public Object getOrderDetailById(@PathVariable Integer orderDetailId) {
        return orderDetailRepository.findById(orderDetailId);
    }

    // Add orderDetail
    @PostMapping("/orderDetail")
    public Object createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    // Delete
    @DeleteMapping("/orderDetail/{orderDetailId}")
    public void deleteOrderDetail(@PathVariable Integer orderDetailId) {
        orderDetailRepository.findById(orderDetailId).map(orderDetail -> {
            orderDetailRepository.delete(orderDetail);
            return ResponseEntity.ok().build();
        });
    }
}


