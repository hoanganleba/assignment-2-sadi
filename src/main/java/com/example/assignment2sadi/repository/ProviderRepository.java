package com.example.assignment2sadi.repository;

import com.example.assignment2sadi.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    Provider findByName(String providerName);
}
