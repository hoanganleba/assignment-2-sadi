package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.Order;
import com.example.assignment2sadi.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    // Get order by order id
    @GetMapping("/order/{orderId}")
    public Object getOrderById(@PathVariable Integer orderId) {
        return orderRepository.findById(orderId);
    }

    // Add order
    @PostMapping("/order")
    public Object createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    // Delete
    @DeleteMapping("/order/{orderId}")
    public void deleteOrder(@PathVariable Integer orderId) {
        orderRepository.findById(orderId).map(order -> {
            orderRepository.delete(order);
            return ResponseEntity.ok().build();
        });
    }
}
