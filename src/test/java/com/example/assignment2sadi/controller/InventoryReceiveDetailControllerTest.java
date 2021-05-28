package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.InventoryReceive;
import com.example.assignment2sadi.model.InventoryReceiveDetail;
import com.example.assignment2sadi.repository.InventoryReceiveDetailRepository;
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
public class InventoryReceiveDetailControllerTest {

    @Mock
    private InventoryReceiveDetailRepository mockInventoryReceiveDetailRepository;
    @Mock
    private InventoryReceiveRepository mockInventoryReceiveRepository;

    private InventoryReceiveDetailController inventoryReceiveDetailControllerUnderTest;

    @Before
    public void setUp() {
        inventoryReceiveDetailControllerUnderTest = new InventoryReceiveDetailController(mockInventoryReceiveDetailRepository, mockInventoryReceiveRepository);
    }

    @Test
    public void testGetInventoryReceiveDetails() {
        // Setup

        // Configure InventoryReceiveDetailRepository.findAll(...).
        final InventoryReceiveDetail inventoryReceiveDetail = new InventoryReceiveDetail();
        inventoryReceiveDetail.setId(0);
        inventoryReceiveDetail.setProduct("product");
        inventoryReceiveDetail.setQuantity(0);
        final InventoryReceive inventoryReceive = new InventoryReceive();
        inventoryReceive.setId(0);
        inventoryReceive.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive.setName("name");
        inventoryReceive.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail.setInventoryReceive(inventoryReceive);
        final List<InventoryReceiveDetail> inventoryReceiveDetails = List.of(inventoryReceiveDetail);
        when(mockInventoryReceiveDetailRepository.findAll()).thenReturn(inventoryReceiveDetails);

        // Run the test
        final List<InventoryReceiveDetail> result = inventoryReceiveDetailControllerUnderTest.getInventoryReceiveDetails();

        // Verify the results
    }

    @Test
    public void testGetInventoryReceiveDetails_InventoryReceiveDetailRepositoryReturnsNoItems() {
        // Setup
        when(mockInventoryReceiveDetailRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<InventoryReceiveDetail> result = inventoryReceiveDetailControllerUnderTest.getInventoryReceiveDetails();

        // Verify the results
    }

    @Test
    public void testGetInventoryReceiveDetailById() {
        // Setup

        // Configure InventoryReceiveDetailRepository.findById(...).
        final InventoryReceiveDetail inventoryReceiveDetail1 = new InventoryReceiveDetail();
        inventoryReceiveDetail1.setId(0);
        inventoryReceiveDetail1.setProduct("product");
        inventoryReceiveDetail1.setQuantity(0);
        final InventoryReceive inventoryReceive = new InventoryReceive();
        inventoryReceive.setId(0);
        inventoryReceive.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive.setName("name");
        inventoryReceive.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail1.setInventoryReceive(inventoryReceive);
        final Optional<InventoryReceiveDetail> inventoryReceiveDetail = Optional.of(inventoryReceiveDetail1);
        when(mockInventoryReceiveDetailRepository.findById(0)).thenReturn(inventoryReceiveDetail);

        // Run the test
        final Object result = inventoryReceiveDetailControllerUnderTest.getInventoryReceiveDetailById(0);

        // Verify the results
    }

    @Test
    public void testGetInventoryReceiveDetailById_InventoryReceiveDetailRepositoryReturnsAbsent() {
        // Setup
        when(mockInventoryReceiveDetailRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Object result = inventoryReceiveDetailControllerUnderTest.getInventoryReceiveDetailById(0);

        // Verify the results
    }

    @Test
    public void testCreateInventoryReceiveDetail() {
        // Setup
        final InventoryReceiveDetail inventoryReceiveDetail = new InventoryReceiveDetail();
        inventoryReceiveDetail.setId(0);
        inventoryReceiveDetail.setProduct("product");
        inventoryReceiveDetail.setQuantity(0);
        final InventoryReceive inventoryReceive = new InventoryReceive();
        inventoryReceive.setId(0);
        inventoryReceive.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive.setName("name");
        inventoryReceive.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail.setInventoryReceive(inventoryReceive);

        // Configure InventoryReceiveDetailRepository.save(...).
        final InventoryReceiveDetail inventoryReceiveDetail1 = new InventoryReceiveDetail();
        inventoryReceiveDetail1.setId(0);
        inventoryReceiveDetail1.setProduct("product");
        inventoryReceiveDetail1.setQuantity(0);
        final InventoryReceive inventoryReceive1 = new InventoryReceive();
        inventoryReceive1.setId(0);
        inventoryReceive1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive1.setName("name");
        inventoryReceive1.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail1.setInventoryReceive(inventoryReceive1);
        when(mockInventoryReceiveDetailRepository.save(any(InventoryReceiveDetail.class))).thenReturn(inventoryReceiveDetail1);

        // Run the test
        final Object result = inventoryReceiveDetailControllerUnderTest.createInventoryReceiveDetail(inventoryReceiveDetail);

        // Verify the results
    }

    @Test
    public void testCreateReceiveDetailByReceiveId() {
        // Setup
        final InventoryReceiveDetail inventoryReceiveDetail = new InventoryReceiveDetail();
        inventoryReceiveDetail.setId(0);
        inventoryReceiveDetail.setProduct("product");
        inventoryReceiveDetail.setQuantity(0);
        final InventoryReceive inventoryReceive = new InventoryReceive();
        inventoryReceive.setId(0);
        inventoryReceive.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive.setName("name");
        inventoryReceive.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail.setInventoryReceive(inventoryReceive);

        // Configure InventoryReceiveRepository.findById(...).
        final InventoryReceive inventoryReceive2 = new InventoryReceive();
        inventoryReceive2.setId(0);
        inventoryReceive2.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive2.setName("name");
        final InventoryReceiveDetail inventoryReceiveDetail1 = new InventoryReceiveDetail();
        inventoryReceiveDetail1.setId(0);
        inventoryReceiveDetail1.setProduct("product");
        inventoryReceiveDetail1.setQuantity(0);
        inventoryReceiveDetail1.setInventoryReceive(new InventoryReceive());
        inventoryReceive2.setInventoryReceiveDetails(List.of(inventoryReceiveDetail1));
        final Optional<InventoryReceive> inventoryReceive1 = Optional.of(inventoryReceive2);
        when(mockInventoryReceiveRepository.findById(0)).thenReturn(inventoryReceive1);

        // Configure InventoryReceiveDetailRepository.save(...).
        final InventoryReceiveDetail inventoryReceiveDetail2 = new InventoryReceiveDetail();
        inventoryReceiveDetail2.setId(0);
        inventoryReceiveDetail2.setProduct("product");
        inventoryReceiveDetail2.setQuantity(0);
        final InventoryReceive inventoryReceive3 = new InventoryReceive();
        inventoryReceive3.setId(0);
        inventoryReceive3.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive3.setName("name");
        inventoryReceive3.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail2.setInventoryReceive(inventoryReceive3);
        when(mockInventoryReceiveDetailRepository.save(any(InventoryReceiveDetail.class))).thenReturn(inventoryReceiveDetail2);

        // Run the test
        final Object result = inventoryReceiveDetailControllerUnderTest.createReceiveDetailByReceiveId(inventoryReceiveDetail, 0);

        // Verify the results
    }

    @Test
    public void testCreateReceiveDetailByReceiveId_InventoryReceiveRepositoryReturnsAbsent() {
        // Setup
        final InventoryReceiveDetail inventoryReceiveDetail = new InventoryReceiveDetail();
        inventoryReceiveDetail.setId(0);
        inventoryReceiveDetail.setProduct("product");
        inventoryReceiveDetail.setQuantity(0);
        final InventoryReceive inventoryReceive = new InventoryReceive();
        inventoryReceive.setId(0);
        inventoryReceive.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive.setName("name");
        inventoryReceive.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail.setInventoryReceive(inventoryReceive);

        when(mockInventoryReceiveRepository.findById(0)).thenReturn(Optional.empty());

        // Configure InventoryReceiveDetailRepository.save(...).
        final InventoryReceiveDetail inventoryReceiveDetail1 = new InventoryReceiveDetail();
        inventoryReceiveDetail1.setId(0);
        inventoryReceiveDetail1.setProduct("product");
        inventoryReceiveDetail1.setQuantity(0);
        final InventoryReceive inventoryReceive1 = new InventoryReceive();
        inventoryReceive1.setId(0);
        inventoryReceive1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive1.setName("name");
        inventoryReceive1.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail1.setInventoryReceive(inventoryReceive1);
        when(mockInventoryReceiveDetailRepository.save(any(InventoryReceiveDetail.class))).thenReturn(inventoryReceiveDetail1);

        // Run the test
        final Object result = inventoryReceiveDetailControllerUnderTest.createReceiveDetailByReceiveId(inventoryReceiveDetail, 0);

        // Verify the results
    }

    @Test
    public void testUpdateInventoryReceiveDetail() {
        // Setup
        final InventoryReceiveDetail newInventoryReceiveDetail = new InventoryReceiveDetail();
        newInventoryReceiveDetail.setId(0);
        newInventoryReceiveDetail.setProduct("product");
        newInventoryReceiveDetail.setQuantity(0);
        final InventoryReceive inventoryReceive = new InventoryReceive();
        inventoryReceive.setId(0);
        inventoryReceive.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive.setName("name");
        inventoryReceive.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        newInventoryReceiveDetail.setInventoryReceive(inventoryReceive);

        // Configure InventoryReceiveDetailRepository.findById(...).
        final InventoryReceiveDetail inventoryReceiveDetail1 = new InventoryReceiveDetail();
        inventoryReceiveDetail1.setId(0);
        inventoryReceiveDetail1.setProduct("product");
        inventoryReceiveDetail1.setQuantity(0);
        final InventoryReceive inventoryReceive1 = new InventoryReceive();
        inventoryReceive1.setId(0);
        inventoryReceive1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive1.setName("name");
        inventoryReceive1.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail1.setInventoryReceive(inventoryReceive1);
        final Optional<InventoryReceiveDetail> inventoryReceiveDetail = Optional.of(inventoryReceiveDetail1);
        when(mockInventoryReceiveDetailRepository.findById(0)).thenReturn(inventoryReceiveDetail);

        // Configure InventoryReceiveDetailRepository.save(...).
        final InventoryReceiveDetail inventoryReceiveDetail2 = new InventoryReceiveDetail();
        inventoryReceiveDetail2.setId(0);
        inventoryReceiveDetail2.setProduct("product");
        inventoryReceiveDetail2.setQuantity(0);
        final InventoryReceive inventoryReceive2 = new InventoryReceive();
        inventoryReceive2.setId(0);
        inventoryReceive2.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive2.setName("name");
        inventoryReceive2.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail2.setInventoryReceive(inventoryReceive2);
        when(mockInventoryReceiveDetailRepository.save(any(InventoryReceiveDetail.class))).thenReturn(inventoryReceiveDetail2);

        // Run the test
        final Object result = inventoryReceiveDetailControllerUnderTest.updateInventoryReceiveDetail(newInventoryReceiveDetail, 0);

        // Verify the results
    }

    @Test
    public void testUpdateInventoryReceiveDetail_InventoryReceiveDetailRepositoryFindByIdReturnsAbsent() {
        // Setup
        final InventoryReceiveDetail newInventoryReceiveDetail = new InventoryReceiveDetail();
        newInventoryReceiveDetail.setId(0);
        newInventoryReceiveDetail.setProduct("product");
        newInventoryReceiveDetail.setQuantity(0);
        final InventoryReceive inventoryReceive = new InventoryReceive();
        inventoryReceive.setId(0);
        inventoryReceive.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive.setName("name");
        inventoryReceive.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        newInventoryReceiveDetail.setInventoryReceive(inventoryReceive);

        when(mockInventoryReceiveDetailRepository.findById(0)).thenReturn(Optional.empty());

        // Configure InventoryReceiveDetailRepository.save(...).
        final InventoryReceiveDetail inventoryReceiveDetail = new InventoryReceiveDetail();
        inventoryReceiveDetail.setId(0);
        inventoryReceiveDetail.setProduct("product");
        inventoryReceiveDetail.setQuantity(0);
        final InventoryReceive inventoryReceive1 = new InventoryReceive();
        inventoryReceive1.setId(0);
        inventoryReceive1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive1.setName("name");
        inventoryReceive1.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail.setInventoryReceive(inventoryReceive1);
        when(mockInventoryReceiveDetailRepository.save(any(InventoryReceiveDetail.class))).thenReturn(inventoryReceiveDetail);

        // Run the test
        final Object result = inventoryReceiveDetailControllerUnderTest.updateInventoryReceiveDetail(newInventoryReceiveDetail, 0);

        // Verify the results
    }

    @Test
    public void testDeleteInventoryReceiveDetail() {
        // Setup

        // Configure InventoryReceiveDetailRepository.findById(...).
        final InventoryReceiveDetail inventoryReceiveDetail1 = new InventoryReceiveDetail();
        inventoryReceiveDetail1.setId(0);
        inventoryReceiveDetail1.setProduct("product");
        inventoryReceiveDetail1.setQuantity(0);
        final InventoryReceive inventoryReceive = new InventoryReceive();
        inventoryReceive.setId(0);
        inventoryReceive.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive.setName("name");
        inventoryReceive.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail1.setInventoryReceive(inventoryReceive);
        final Optional<InventoryReceiveDetail> inventoryReceiveDetail = Optional.of(inventoryReceiveDetail1);
        when(mockInventoryReceiveDetailRepository.findById(0)).thenReturn(inventoryReceiveDetail);

        // Run the test
        inventoryReceiveDetailControllerUnderTest.deleteInventoryReceiveDetail(0);

        // Verify the results
        verify(mockInventoryReceiveDetailRepository).delete(any(InventoryReceiveDetail.class));
    }

    @Test
    public void testDeleteInventoryReceiveDetail_InventoryReceiveDetailRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockInventoryReceiveDetailRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        inventoryReceiveDetailControllerUnderTest.deleteInventoryReceiveDetail(0);

        // Verify the results
        verify(mockInventoryReceiveDetailRepository).delete(any(InventoryReceiveDetail.class));
    }
}
