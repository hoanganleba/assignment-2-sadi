package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.Product;
import com.example.assignment2sadi.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // Get product by product id
    @GetMapping("/product/{productId}")
    public Object getProductById(@PathVariable Integer productId) {
        return productRepository.findById(productId);
    }

    // Add product
    @PostMapping("/product")
    public Object createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Delete
    @DeleteMapping("/product/{productId}")
    public void deleteProduct(@PathVariable Integer productId) {
        productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        });
    }
}
