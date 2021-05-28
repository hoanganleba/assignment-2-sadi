package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.InventoryReceive;
import com.example.assignment2sadi.model.InventoryReceiveDetail;
import com.example.assignment2sadi.repository.InventoryReceiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InventoryReceiveControllerTest {

    @Mock
    private InventoryReceiveRepository mockInventoryReceiveRepository;

    private InventoryReceiveController inventoryReceiveControllerUnderTest;

    @Before
    public void setUp() {
        inventoryReceiveControllerUnderTest = new InventoryReceiveController(mockInventoryReceiveRepository);
    }

    @Test
    public void testGetInventoryReceives() {
        // Setup

        // Configure InventoryReceiveRepository.findAll(...).
        final InventoryReceive inventoryReceive = new InventoryReceive();
        inventoryReceive.setId(0);
        inventoryReceive.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive.setName("name");
        final InventoryReceiveDetail inventoryReceiveDetail = new InventoryReceiveDetail();
        inventoryReceiveDetail.setId(0);
        inventoryReceiveDetail.setProduct("product");
        inventoryReceiveDetail.setQuantity(0);
        inventoryReceiveDetail.setInventoryReceive(new InventoryReceive());
        inventoryReceive.setInventoryReceiveDetails(List.of(inventoryReceiveDetail));
        final List<InventoryReceive> inventoryReceives = List.of(inventoryReceive);
        when(mockInventoryReceiveRepository.findAll()).thenReturn(inventoryReceives);

        // Run the test
        final List<InventoryReceive> result = inventoryReceiveControllerUnderTest.getInventoryReceives();

        // Verify the results
    }

    @Test
    public void testGetInventoryReceives_InventoryReceiveRepositoryReturnsNoItems() {
        // Setup
        when(mockInventoryReceiveRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<InventoryReceive> result = inventoryReceiveControllerUnderTest.getInventoryReceives();

        // Verify the results
    }

    @Test
    public void testGetInventoryReceiveById() {
        // Setup

        // Configure InventoryReceiveRepository.findById(...).
        final InventoryReceive inventoryReceive1 = new InventoryReceive();
        inventoryReceive1.setId(0);
        inventoryReceive1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive1.setName("name");
        final InventoryReceiveDetail inventoryReceiveDetail = new InventoryReceiveDetail();
        inventoryReceiveDetail.setId(0);
        inventoryReceiveDetail.setProduct("product");
        inventoryReceiveDetail.setQuantity(0);
        inventoryReceiveDetail.setInventoryReceive(new InventoryReceive());
        inventoryReceive1.setInventoryReceiveDetails(List.of(inventoryReceiveDetail));
        final Optional<InventoryReceive> inventoryReceive = Optional.of(inventoryReceive1);
        when(mockInventoryReceiveRepository.findById(0)).thenReturn(inventoryReceive);

        // Run the test
        final Object result = inventoryReceiveControllerUnderTest.getInventoryReceiveById(0);

        // Verify the results
    }

    @Test
    public void testGetInventoryReceiveById_InventoryReceiveRepositoryReturnsAbsent() {
        // Setup
        when(mockInventoryReceiveRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Object result = inventoryReceiveControllerUnderTest.getInventoryReceiveById(0);

        // Verify the results
    }

    @Test
    public void testCreateInventoryReceive() {
        // Setup
        final InventoryReceive inventoryReceive = new InventoryReceive();
        inventoryReceive.setId(0);
        inventoryReceive.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive.setName("name");
        final InventoryReceiveDetail inventoryReceiveDetail = new InventoryReceiveDetail();
        inventoryReceiveDetail.setId(0);
        inventoryReceiveDetail.setProduct("product");
        inventoryReceiveDetail.setQuantity(0);
        inventoryReceiveDetail.setInventoryReceive(new InventoryReceive());
        inventoryReceive.setInventoryReceiveDetails(List.of(inventoryReceiveDetail));

        // Configure InventoryReceiveRepository.save(...).
        final InventoryReceive inventoryReceive1 = new InventoryReceive();
        inventoryReceive1.setId(0);
        inventoryReceive1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive1.setName("name");
        final InventoryReceiveDetail inventoryReceiveDetail1 = new InventoryReceiveDetail();
        inventoryReceiveDetail1.setId(0);
        inventoryReceiveDetail1.setProduct("product");
        inventoryReceiveDetail1.setQuantity(0);
        inventoryReceiveDetail1.setInventoryReceive(new InventoryReceive());
        inventoryReceive1.setInventoryReceiveDetails(List.of(inventoryReceiveDetail1));
        when(mockInventoryReceiveRepository.save(any(InventoryReceive.class))).thenReturn(inventoryReceive1);

        // Run the test
        final Object result = inventoryReceiveControllerUnderTest.createInventoryReceive(inventoryReceive);

        // Verify the results
    }

    @Test
    public void testUpdateInventoryReceive() {
        // Setup
        final InventoryReceive newInventoryReceive = new InventoryReceive();
        newInventoryReceive.setId(0);
        newInventoryReceive.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        newInventoryReceive.setName("name");
        final InventoryReceiveDetail inventoryReceiveDetail = new InventoryReceiveDetail();
        inventoryReceiveDetail.setId(0);
        inventoryReceiveDetail.setProduct("product");
        inventoryReceiveDetail.setQuantity(0);
        inventoryReceiveDetail.setInventoryReceive(new InventoryReceive());
        newInventoryReceive.setInventoryReceiveDetails(List.of(inventoryReceiveDetail));

        // Configure InventoryReceiveRepository.findById(...).
        final InventoryReceive inventoryReceive1 = new InventoryReceive();
        inventoryReceive1.setId(0);
        inventoryReceive1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive1.setName("name");
        final InventoryReceiveDetail inventoryReceiveDetail1 = new InventoryReceiveDetail();
        inventoryReceiveDetail1.setId(0);
        inventoryReceiveDetail1.setProduct("product");
        inventoryReceiveDetail1.setQuantity(0);
        inventoryReceiveDetail1.setInventoryReceive(new InventoryReceive());
        inventoryReceive1.setInventoryReceiveDetails(List.of(inventoryReceiveDetail1));
        final Optional<InventoryReceive> inventoryReceive = Optional.of(inventoryReceive1);
        when(mockInventoryReceiveRepository.findById(0)).thenReturn(inventoryReceive);

        // Configure InventoryReceiveRepository.save(...).
        final InventoryReceive inventoryReceive2 = new InventoryReceive();
        inventoryReceive2.setId(0);
        inventoryReceive2.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive2.setName("name");
        final InventoryReceiveDetail inventoryReceiveDetail2 = new InventoryReceiveDetail();
        inventoryReceiveDetail2.setId(0);
        inventoryReceiveDetail2.setProduct("product");
        inventoryReceiveDetail2.setQuantity(0);
        inventoryReceiveDetail2.setInventoryReceive(new InventoryReceive());
        inventoryReceive2.setInventoryReceiveDetails(List.of(inventoryReceiveDetail2));
        when(mockInventoryReceiveRepository.save(any(InventoryReceive.class))).thenReturn(inventoryReceive2);

        // Run the test
        final Object result = inventoryReceiveControllerUnderTest.updateInventoryReceive(newInventoryReceive, 0);

        // Verify the results
    }

    @Test
    public void testUpdateInventoryReceive_InventoryReceiveRepositoryFindByIdReturnsAbsent() {
        // Setup
        final InventoryReceive newInventoryReceive = new InventoryReceive();
        newInventoryReceive.setId(0);
        newInventoryReceive.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        newInventoryReceive.setName("name");
        final InventoryReceiveDetail inventoryReceiveDetail = new InventoryReceiveDetail();
        inventoryReceiveDetail.setId(0);
        inventoryReceiveDetail.setProduct("product");
        inventoryReceiveDetail.setQuantity(0);
        inventoryReceiveDetail.setInventoryReceive(new InventoryReceive());
        newInventoryReceive.setInventoryReceiveDetails(List.of(inventoryReceiveDetail));

        when(mockInventoryReceiveRepository.findById(0)).thenReturn(Optional.empty());

        // Configure InventoryReceiveRepository.save(...).
        final InventoryReceive inventoryReceive = new InventoryReceive();
        inventoryReceive.setId(0);
        inventoryReceive.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive.setName("name");
        final InventoryReceiveDetail inventoryReceiveDetail1 = new InventoryReceiveDetail();
        inventoryReceiveDetail1.setId(0);
        inventoryReceiveDetail1.setProduct("product");
        inventoryReceiveDetail1.setQuantity(0);
        inventoryReceiveDetail1.setInventoryReceive(new InventoryReceive());
        inventoryReceive.setInventoryReceiveDetails(List.of(inventoryReceiveDetail1));
        when(mockInventoryReceiveRepository.save(any(InventoryReceive.class))).thenReturn(inventoryReceive);

        // Run the test
        final Object result = inventoryReceiveControllerUnderTest.updateInventoryReceive(newInventoryReceive, 0);

        // Verify the results
    }

    @Test
    public void testDeleteInventoryReceive() {
        // Setup

        // Configure InventoryReceiveRepository.findById(...).
        final InventoryReceive inventoryReceive1 = new InventoryReceive();
        inventoryReceive1.setId(0);
        inventoryReceive1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive1.setName("name");
        final InventoryReceiveDetail inventoryReceiveDetail = new InventoryReceiveDetail();
        inventoryReceiveDetail.setId(0);
        inventoryReceiveDetail.setProduct("product");
        inventoryReceiveDetail.setQuantity(0);
        inventoryReceiveDetail.setInventoryReceive(new InventoryReceive());
        inventoryReceive1.setInventoryReceiveDetails(List.of(inventoryReceiveDetail));
        final Optional<InventoryReceive> inventoryReceive = Optional.of(inventoryReceive1);
        when(mockInventoryReceiveRepository.findById(0)).thenReturn(inventoryReceive);

        // Run the test
        inventoryReceiveControllerUnderTest.deleteInventoryReceive(0);

        // Verify the results
        verify(mockInventoryReceiveRepository).delete(any(InventoryReceive.class));
    }

    @Test
    public void testDeleteInventoryReceive_InventoryReceiveRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockInventoryReceiveRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        inventoryReceiveControllerUnderTest.deleteInventoryReceive(0);

        // Verify the results
        verify(mockInventoryReceiveRepository).delete(any(InventoryReceive.class));
    }
}
