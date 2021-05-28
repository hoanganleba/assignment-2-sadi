package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.*;
import com.example.assignment2sadi.repository.SaleDetailRepository;
import com.example.assignment2sadi.repository.SaleRepository;
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
public class SaleDetailControllerTest {

    @Mock
    private SaleDetailRepository mockSaleDetailRepository;

    @Mock
    private SaleRepository mockSaleRepository;

    private SaleDetailController saleDetailControllerUnderTest;

    @Before
    public void setUp() {
        saleDetailControllerUnderTest = new SaleDetailController(mockSaleDetailRepository, mockSaleRepository);
    }

    @Test
    public void testGetSaleDetails() {
        // Setup

        // Configure SaleDetailRepository.findAll(...).
        final SaleDetail saleDetail = new SaleDetail();
        saleDetail.setId(0);
        saleDetail.setProduct("product");
        saleDetail.setQuantity("quantity");
        saleDetail.setPrice("price");
        final Sale sale = new Sale();
        sale.setId(0);
        sale.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        sale.setTotalPrice("totalPrice");
        final Customer customer = new Customer();
        customer.setId(0);
        customer.setName("name");
        customer.setAddress("address");
        customer.setPhoneNumber("phoneNumber");
        customer.setFax("fax");
        customer.setEmail("email");
        customer.setContactPerson("contactPerson");
        customer.setSales(List.of(new Sale()));
        sale.setCustomer(customer);
        final Staff staff = new Staff();
        staff.setId(0);
        staff.setName("name");
        staff.setAddress("address");
        staff.setPhoneNumber("phoneNumber");
        staff.setEmail("email");
        final Order order = new Order();
        order.setId(0);
        order.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setStaff(new Staff());
        final Provider provider = new Provider();
        provider.setId(0);
        provider.setName("name");
        provider.setAddress("address");
        provider.setPhoneNumber("phoneNumber");
        provider.setFax("fax");
        provider.setEmail("email");
        provider.setContactPerson("contactPerson");
        provider.setOrder(List.of(new Order()));
        order.setProvider(provider);
        final OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(0);
        orderDetail.setProduct("product");
        orderDetail.setQuantity(0);
        orderDetail.setPrice("price");
        orderDetail.setOrder(new Order());
        order.setOrderDetails(List.of(orderDetail));
        staff.setOrder(List.of(order));
        staff.setSales(List.of(new Sale()));
        sale.setStaff(staff);
        sale.setSaleDetails(List.of(new SaleDetail()));
        saleDetail.setSale(sale);
        final List<SaleDetail> saleDetails = List.of(saleDetail);
        when(mockSaleDetailRepository.findAll()).thenReturn(saleDetails);

        // Run the test
        final List<SaleDetail> result = saleDetailControllerUnderTest.getSaleDetails();

        // Verify the results
    }

    @Test
    public void testGetSaleDetails_SaleDetailRepositoryReturnsNoItems() {
        // Setup
        when(mockSaleDetailRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<SaleDetail> result = saleDetailControllerUnderTest.getSaleDetails();

        // Verify the results
    }

    @Test
    public void testGetSaleDetailById() {
        // Setup

        // Configure SaleDetailRepository.findById(...).
        final SaleDetail saleDetail1 = new SaleDetail();
        saleDetail1.setId(0);
        saleDetail1.setProduct("product");
        saleDetail1.setQuantity("quantity");
        saleDetail1.setPrice("price");
        final Sale sale = new Sale();
        sale.setId(0);
        sale.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        sale.setTotalPrice("totalPrice");
        final Customer customer = new Customer();
        customer.setId(0);
        customer.setName("name");
        customer.setAddress("address");
        customer.setPhoneNumber("phoneNumber");
        customer.setFax("fax");
        customer.setEmail("email");
        customer.setContactPerson("contactPerson");
        customer.setSales(List.of(new Sale()));
        sale.setCustomer(customer);
        final Staff staff = new Staff();
        staff.setId(0);
        staff.setName("name");
        staff.setAddress("address");
        staff.setPhoneNumber("phoneNumber");
        staff.setEmail("email");
        final Order order = new Order();
        order.setId(0);
        order.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setStaff(new Staff());
        final Provider provider = new Provider();
        provider.setId(0);
        provider.setName("name");
        provider.setAddress("address");
        provider.setPhoneNumber("phoneNumber");
        provider.setFax("fax");
        provider.setEmail("email");
        provider.setContactPerson("contactPerson");
        provider.setOrder(List.of(new Order()));
        order.setProvider(provider);
        final OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(0);
        orderDetail.setProduct("product");
        orderDetail.setQuantity(0);
        orderDetail.setPrice("price");
        orderDetail.setOrder(new Order());
        order.setOrderDetails(List.of(orderDetail));
        staff.setOrder(List.of(order));
        staff.setSales(List.of(new Sale()));
        sale.setStaff(staff);
        sale.setSaleDetails(List.of(new SaleDetail()));
        saleDetail1.setSale(sale);
        final Optional<SaleDetail> saleDetail = Optional.of(saleDetail1);
        when(mockSaleDetailRepository.findById(0)).thenReturn(saleDetail);

        // Run the test
        final Object result = saleDetailControllerUnderTest.getSaleDetailById(0);

        // Verify the results
    }

    @Test
    public void testGetSaleDetailById_SaleDetailRepositoryReturnsAbsent() {
        // Setup
        when(mockSaleDetailRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Object result = saleDetailControllerUnderTest.getSaleDetailById(0);

        // Verify the results
    }

    @Test
    public void testCreateSaleDetail() {
        // Setup
        final SaleDetail saleDetail = new SaleDetail();
        saleDetail.setId(0);
        saleDetail.setProduct("product");
        saleDetail.setQuantity("quantity");
        saleDetail.setPrice("price");
        final Sale sale = new Sale();
        sale.setId(0);
        sale.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        sale.setTotalPrice("totalPrice");
        final Customer customer = new Customer();
        customer.setId(0);
        customer.setName("name");
        customer.setAddress("address");
        customer.setPhoneNumber("phoneNumber");
        customer.setFax("fax");
        customer.setEmail("email");
        customer.setContactPerson("contactPerson");
        customer.setSales(List.of(new Sale()));
        sale.setCustomer(customer);
        final Staff staff = new Staff();
        staff.setId(0);
        staff.setName("name");
        staff.setAddress("address");
        staff.setPhoneNumber("phoneNumber");
        staff.setEmail("email");
        final Order order = new Order();
        order.setId(0);
        order.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setStaff(new Staff());
        final Provider provider = new Provider();
        provider.setId(0);
        provider.setName("name");
        provider.setAddress("address");
        provider.setPhoneNumber("phoneNumber");
        provider.setFax("fax");
        provider.setEmail("email");
        provider.setContactPerson("contactPerson");
        provider.setOrder(List.of(new Order()));
        order.setProvider(provider);
        final OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(0);
        orderDetail.setProduct("product");
        orderDetail.setQuantity(0);
        orderDetail.setPrice("price");
        orderDetail.setOrder(new Order());
        order.setOrderDetails(List.of(orderDetail));
        staff.setOrder(List.of(order));
        staff.setSales(List.of(new Sale()));
        sale.setStaff(staff);
        sale.setSaleDetails(List.of(new SaleDetail()));
        saleDetail.setSale(sale);

        // Configure SaleDetailRepository.save(...).
        final SaleDetail saleDetail1 = new SaleDetail();
        saleDetail1.setId(0);
        saleDetail1.setProduct("product");
        saleDetail1.setQuantity("quantity");
        saleDetail1.setPrice("price");
        final Sale sale1 = new Sale();
        sale1.setId(0);
        sale1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        sale1.setTotalPrice("totalPrice");
        final Customer customer1 = new Customer();
        customer1.setId(0);
        customer1.setName("name");
        customer1.setAddress("address");
        customer1.setPhoneNumber("phoneNumber");
        customer1.setFax("fax");
        customer1.setEmail("email");
        customer1.setContactPerson("contactPerson");
        customer1.setSales(List.of(new Sale()));
        sale1.setCustomer(customer1);
        final Staff staff1 = new Staff();
        staff1.setId(0);
        staff1.setName("name");
        staff1.setAddress("address");
        staff1.setPhoneNumber("phoneNumber");
        staff1.setEmail("email");
        final Order order1 = new Order();
        order1.setId(0);
        order1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order1.setStaff(new Staff());
        final Provider provider1 = new Provider();
        provider1.setId(0);
        provider1.setName("name");
        provider1.setAddress("address");
        provider1.setPhoneNumber("phoneNumber");
        provider1.setFax("fax");
        provider1.setEmail("email");
        provider1.setContactPerson("contactPerson");
        provider1.setOrder(List.of(new Order()));
        order1.setProvider(provider1);
        final OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setId(0);
        orderDetail1.setProduct("product");
        orderDetail1.setQuantity(0);
        orderDetail1.setPrice("price");
        orderDetail1.setOrder(new Order());
        order1.setOrderDetails(List.of(orderDetail1));
        staff1.setOrder(List.of(order1));
        staff1.setSales(List.of(new Sale()));
        sale1.setStaff(staff1);
        sale1.setSaleDetails(List.of(new SaleDetail()));
        saleDetail1.setSale(sale1);
        when(mockSaleDetailRepository.save(any(SaleDetail.class))).thenReturn(saleDetail1);

        // Run the test
        final Object result = saleDetailControllerUnderTest.createSaleDetail(saleDetail);

        // Verify the results
    }

    @Test
    public void testDeleteSaleDetail() {
        // Setup

        // Configure SaleDetailRepository.findById(...).
        final SaleDetail saleDetail1 = new SaleDetail();
        saleDetail1.setId(0);
        saleDetail1.setProduct("product");
        saleDetail1.setQuantity("quantity");
        saleDetail1.setPrice("price");
        final Sale sale = new Sale();
        sale.setId(0);
        sale.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        sale.setTotalPrice("totalPrice");
        final Customer customer = new Customer();
        customer.setId(0);
        customer.setName("name");
        customer.setAddress("address");
        customer.setPhoneNumber("phoneNumber");
        customer.setFax("fax");
        customer.setEmail("email");
        customer.setContactPerson("contactPerson");
        customer.setSales(List.of(new Sale()));
        sale.setCustomer(customer);
        final Staff staff = new Staff();
        staff.setId(0);
        staff.setName("name");
        staff.setAddress("address");
        staff.setPhoneNumber("phoneNumber");
        staff.setEmail("email");
        final Order order = new Order();
        order.setId(0);
        order.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order.setStaff(new Staff());
        final Provider provider = new Provider();
        provider.setId(0);
        provider.setName("name");
        provider.setAddress("address");
        provider.setPhoneNumber("phoneNumber");
        provider.setFax("fax");
        provider.setEmail("email");
        provider.setContactPerson("contactPerson");
        provider.setOrder(List.of(new Order()));
        order.setProvider(provider);
        final OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(0);
        orderDetail.setProduct("product");
        orderDetail.setQuantity(0);
        orderDetail.setPrice("price");
        orderDetail.setOrder(new Order());
        order.setOrderDetails(List.of(orderDetail));
        staff.setOrder(List.of(order));
        staff.setSales(List.of(new Sale()));
        sale.setStaff(staff);
        sale.setSaleDetails(List.of(new SaleDetail()));
        saleDetail1.setSale(sale);
        final Optional<SaleDetail> saleDetail = Optional.of(saleDetail1);
        when(mockSaleDetailRepository.findById(0)).thenReturn(saleDetail);

        // Run the test
        saleDetailControllerUnderTest.deleteSaleDetail(0);

        // Verify the results
        verify(mockSaleDetailRepository).delete(any(SaleDetail.class));
    }

    @Test
    public void testDeleteSaleDetail_SaleDetailRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockSaleDetailRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        saleDetailControllerUnderTest.deleteSaleDetail(0);

        // Verify the results
        verify(mockSaleDetailRepository).delete(any(SaleDetail.class));
    }
}
