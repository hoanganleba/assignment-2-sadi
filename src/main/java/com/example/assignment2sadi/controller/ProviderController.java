package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.Provider;
import com.example.assignment2sadi.repository.ProviderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ProviderController {

    private final ProviderRepository providerRepository;

    public ProviderController(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @GetMapping("/providers")
    public List<Provider> getProviders() {
        return providerRepository.findAll();
    }

    // Get provider by provider id
    @GetMapping("/provider/{providerId}")
    public Object getProviderById(@PathVariable Integer providerId) {
        return providerRepository.findById(providerId);
    }

    // Add provider
    @PostMapping("/provider")
    public Object createProvider(@RequestBody Provider provider) {
        return providerRepository.save(provider);
    }

    // Update
    @PutMapping("/provider/{providerId}")
    public Object updateProvider(@RequestBody Provider newProvider, @PathVariable Integer providerId) {
        return providerRepository.findById(providerId).map(provider -> {
            provider.setName(newProvider.getName());
            provider.setAddress(newProvider.getAddress());
            provider.setEmail(newProvider.getEmail());
            provider.setPhoneNumber(newProvider.getPhoneNumber());
            provider.setFax(newProvider.getFax());
            provider.setEmail(newProvider.getEmail());
            provider.setContactPerson(newProvider.getContactPerson());
            providerRepository.save(provider);
            return ResponseEntity.ok(newProvider);
        });
    }

    // Delete
    @DeleteMapping("/provider/{providerId}")
    public void deleteProvider(@PathVariable Integer providerId) {
        providerRepository.findById(providerId).map(provider -> {
            providerRepository.delete(provider);
            return ResponseEntity.ok().build();
        });
    }
}
