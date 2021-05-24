package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.Order;
import com.example.assignment2sadi.repository.OrderRepository;
import com.example.assignment2sadi.repository.ProviderRepository;
import com.example.assignment2sadi.repository.StaffRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class OrderController {

    private final OrderRepository orderRepository;
    private final StaffRepository staffRepository;
    private final ProviderRepository providerRepository;

    public OrderController(OrderRepository orderRepository, StaffRepository staffRepository, ProviderRepository providerRepository) {
        this.orderRepository = orderRepository;
        this.staffRepository = staffRepository;
        this.providerRepository = providerRepository;
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
    @PostMapping("/order/{staffId}/{providerId}")
    public Object createOrder(@RequestBody Order order, @PathVariable Integer staffId, @PathVariable Integer providerId) {
        return staffRepository.findById(staffId).map(staff -> {
            order.setStaff(staff);
            return providerRepository.findById(providerId).map(provider -> {
                order.setProvider(provider);
                return orderRepository.save(order);
            });
        });
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
