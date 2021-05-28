package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.OrderDetail;
import com.example.assignment2sadi.repository.OrderDetailRepository;
import com.example.assignment2sadi.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class OrderDetailController {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;

    public OrderDetailController(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
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
    @PostMapping("/order/{orderId}/orderDetail")
    public Object createOrderDetailByOrderId(@RequestBody OrderDetail orderDetail, @PathVariable Integer orderId) {
        return orderRepository.findById(orderId).map(order -> {
            orderDetail.setOrder(order);
            return orderDetailRepository.save(orderDetail);
        });
    }

    @PutMapping("/orderDetail/{orderDetailId}")
    public Object updateOrderDetail(@RequestBody OrderDetail newOrderDetail, @PathVariable Integer orderDetailId) {
        return orderDetailRepository.findById(orderDetailId).map(orderDetail -> {
            orderDetail.setPrice(newOrderDetail.getPrice());
            orderDetail.setProduct(newOrderDetail.getProduct());
            orderDetail.setQuantity(newOrderDetail.getQuantity());
            return orderDetailRepository.save(orderDetail);
        });
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


