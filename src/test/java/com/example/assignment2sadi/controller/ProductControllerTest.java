package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.Category;
import com.example.assignment2sadi.model.Product;
import com.example.assignment2sadi.repository.ProductRepository;
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
public class ProductControllerTest {

    @Mock
    private ProductRepository mockProductRepository;

    private ProductController productControllerUnderTest;

    @Before
    public void setUp() {
        productControllerUnderTest = new ProductController(mockProductRepository);
    }

    @Test
    public void testGetProducts() {
        // Setup

        // Configure ProductRepository.findAll(...).
        final Product product = new Product();
        product.setId(0);
        product.setName("name");
        product.setModel("model");
        product.setBrand("brand");
        product.setCompany("company");
        product.setDescription("description");
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        category.setProduct(List.of(new Product()));
        product.setCategory(category);
        product.setPrice("price");
        final List<Product> products = List.of(product);
        when(mockProductRepository.findAll()).thenReturn(products);

        // Run the test
        final List<Product> result = productControllerUnderTest.getProducts();

        // Verify the results
    }

    @Test
    public void testGetProducts_ProductRepositoryReturnsNoItems() {
        // Setup
        when(mockProductRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Product> result = productControllerUnderTest.getProducts();

        // Verify the results
    }

    @Test
    public void testGetProductById() {
        // Setup

        // Configure ProductRepository.findById(...).
        final Product product1 = new Product();
        product1.setId(0);
        product1.setName("name");
        product1.setModel("model");
        product1.setBrand("brand");
        product1.setCompany("company");
        product1.setDescription("description");
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        category.setProduct(List.of(new Product()));
        product1.setCategory(category);
        product1.setPrice("price");
        final Optional<Product> product = Optional.of(product1);
        when(mockProductRepository.findById(0)).thenReturn(product);

        // Run the test
        final Object result = productControllerUnderTest.getProductById(0);

        // Verify the results
    }

    @Test
    public void testGetProductById_ProductRepositoryReturnsAbsent() {
        // Setup
        when(mockProductRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Object result = productControllerUnderTest.getProductById(0);

        // Verify the results
    }

    @Test
    public void testCreateProduct() {
        // Setup
        final Product product = new Product();
        product.setId(0);
        product.setName("name");
        product.setModel("model");
        product.setBrand("brand");
        product.setCompany("company");
        product.setDescription("description");
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        category.setProduct(List.of(new Product()));
        product.setCategory(category);
        product.setPrice("price");

        // Configure ProductRepository.save(...).
        final Product product1 = new Product();
        product1.setId(0);
        product1.setName("name");
        product1.setModel("model");
        product1.setBrand("brand");
        product1.setCompany("company");
        product1.setDescription("description");
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        category1.setProduct(List.of(new Product()));
        product1.setCategory(category1);
        product1.setPrice("price");
        when(mockProductRepository.save(any(Product.class))).thenReturn(product1);

        // Run the test
        final Object result = productControllerUnderTest.createProduct(product);

        // Verify the results
    }

    @Test
    public void testDeleteProduct() {
        // Setup

        // Configure ProductRepository.findById(...).
        final Product product1 = new Product();
        product1.setId(0);
        product1.setName("name");
        product1.setModel("model");
        product1.setBrand("brand");
        product1.setCompany("company");
        product1.setDescription("description");
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        category.setProduct(List.of(new Product()));
        product1.setCategory(category);
        product1.setPrice("price");
        final Optional<Product> product = Optional.of(product1);
        when(mockProductRepository.findById(0)).thenReturn(product);

        // Run the test
        productControllerUnderTest.deleteProduct(0);

        // Verify the results
        verify(mockProductRepository).delete(any(Product.class));
    }

    @Test
    public void testDeleteProduct_ProductRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockProductRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        productControllerUnderTest.deleteProduct(0);

        // Verify the results
        verify(mockProductRepository).delete(any(Product.class));
    }
}
