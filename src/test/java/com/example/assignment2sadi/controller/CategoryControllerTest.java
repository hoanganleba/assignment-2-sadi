package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.Category;
import com.example.assignment2sadi.model.Product;
import com.example.assignment2sadi.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @Mock
    private CategoryRepository mockCategoryRepository;

    private CategoryController categoryControllerUnderTest;

    @Before
    public void setUp() throws Exception {
        categoryControllerUnderTest = new CategoryController(mockCategoryRepository);
    }

    @Test
    public void testGetCategories() {
        // Setup

        // Configure CategoryRepository.findAll(...).
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        final Product product = new Product();
        product.setId(0);
        product.setName("name");
        product.setModel("model");
        product.setBrand("brand");
        product.setCompany("company");
        product.setDescription("description");
        product.setCategory(new Category());
        product.setPrice("price");
        category.setProduct(List.of(product));
        final List<Category> categories = List.of(category);
        when(mockCategoryRepository.findAll()).thenReturn(categories);

        // Run the test
        final List<Category> result = categoryControllerUnderTest.getCategories();

        // Verify the results
    }

    @Test
    public void testGetCategories_CategoryRepositoryReturnsNoItems() {
        // Setup
        when(mockCategoryRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Category> result = categoryControllerUnderTest.getCategories();

        // Verify the results
    }

    @Test
    public void testGetCategoryById() {
        // Setup

        // Configure CategoryRepository.findById(...).
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        final Product product = new Product();
        product.setId(0);
        product.setName("name");
        product.setModel("model");
        product.setBrand("brand");
        product.setCompany("company");
        product.setDescription("description");
        product.setCategory(new Category());
        product.setPrice("price");
        category1.setProduct(List.of(product));
        final Optional<Category> category = Optional.of(category1);
        when(mockCategoryRepository.findById(0)).thenReturn(category);

        // Run the test
        final Object result = categoryControllerUnderTest.getCategoryById(0);

        // Verify the results
    }

    @Test
    public void testGetCategoryById_CategoryRepositoryReturnsAbsent() {
        // Setup
        when(mockCategoryRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Object result = categoryControllerUnderTest.getCategoryById(0);

        // Verify the results
    }

    @Test
    public void testCreateCategory() {
        // Setup
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        final Product product = new Product();
        product.setId(0);
        product.setName("name");
        product.setModel("model");
        product.setBrand("brand");
        product.setCompany("company");
        product.setDescription("description");
        product.setCategory(new Category());
        product.setPrice("price");
        category.setProduct(List.of(product));

        // Configure CategoryRepository.save(...).
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        final Product product1 = new Product();
        product1.setId(0);
        product1.setName("name");
        product1.setModel("model");
        product1.setBrand("brand");
        product1.setCompany("company");
        product1.setDescription("description");
        product1.setCategory(new Category());
        product1.setPrice("price");
        category1.setProduct(List.of(product1));
        when(mockCategoryRepository.save(any(Category.class))).thenReturn(category1);

        // Run the test
        final Object result = categoryControllerUnderTest.createCategory(category);

        // Verify the results
    }

    @Test
    public void testDeleteCategory() {
        // Setup

        // Configure CategoryRepository.findById(...).
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        final Product product = new Product();
        product.setId(0);
        product.setName("name");
        product.setModel("model");
        product.setBrand("brand");
        product.setCompany("company");
        product.setDescription("description");
        product.setCategory(new Category());
        product.setPrice("price");
        category1.setProduct(List.of(product));
        final Optional<Category> category = Optional.of(category1);
        when(mockCategoryRepository.findById(0)).thenReturn(category);

        // Run the test
        categoryControllerUnderTest.deleteCategory(0);

        // Verify the results
        verify(mockCategoryRepository).delete(any(Category.class));
    }
}
