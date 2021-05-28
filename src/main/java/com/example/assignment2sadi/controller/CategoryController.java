package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.Category;
import com.example.assignment2sadi.model.Customer;
import com.example.assignment2sadi.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    // Get Category by Category id
    @GetMapping("/category/{categoryId}")
    public Object getCategoryById(@PathVariable Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }

    // Add Category
    @PostMapping("/category")
    public Object createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    // Update
    @PutMapping("/category/{categoryId}")
    public Object updateCategory(@RequestBody Category newCategory, @PathVariable Integer categoryId) {
        return categoryRepository.findById(categoryId).map(category -> {
            category.setName(newCategory.getName());
            categoryRepository.save(category);
            return ResponseEntity.ok(newCategory);
        });
    }

    // Delete
    @DeleteMapping("/category/{categoryId}")
    public void deleteCategory(@PathVariable Integer categoryId) {
        categoryRepository.findById(categoryId).map(Category -> {
            categoryRepository.delete(Category);
            return ResponseEntity.ok().build();
        });
    }
}
