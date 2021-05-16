package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.Customer;
import com.example.assignment2sadi.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by customer id
    @GetMapping("/customer/{customerId}")
    public Object getCustomerById(@PathVariable Integer customerId) {
        return customerRepository.findById(customerId);
    }

    // Add customer
    @PostMapping("/customer")
    public Object createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // Update
    @PutMapping("/customer/{customerId}")
    public Object updateCustomer(@RequestBody Customer newCustomer, @PathVariable Integer customerId) {
        return customerRepository.findById(customerId).map(customer -> {
            customer.setName(newCustomer.getName());
            customer.setAddress(newCustomer.getAddress());
            customer.setEmail(newCustomer.getEmail());
            customer.setPhoneNumber(newCustomer.getPhoneNumber());
            customer.setFax(newCustomer.getFax());
            customer.setEmail(newCustomer.getEmail());
            customer.setContactPerson(newCustomer.getContactPerson());
            customerRepository.save(customer);
            return ResponseEntity.ok(newCustomer);
        });
    }

    // Delete
    @DeleteMapping("/customer/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId) {
        customerRepository.findById(customerId).map(customer -> {
            customerRepository.delete(customer);
            return ResponseEntity.ok().build();
        });
    }
}
