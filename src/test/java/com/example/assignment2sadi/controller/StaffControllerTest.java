package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.*;
import com.example.assignment2sadi.repository.StaffRepository;
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
public class StaffControllerTest {

    @Mock
    private StaffRepository mockStaffRepository;

    private StaffController staffControllerUnderTest;

    @Before
    public void setUp() {
        staffControllerUnderTest = new StaffController(mockStaffRepository);
    }

    @Test
    public void testGetStaffs() {
        // Setup

        // Configure StaffRepository.findAll(...).
        final Staff staff1 = new Staff();
        staff1.setId(0);
        staff1.setName("name");
        staff1.setAddress("address");
        staff1.setPhoneNumber("phoneNumber");
        staff1.setEmail("email");
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
        staff1.setOrder(List.of(order));
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
        sale.setStaff(new Staff());
        final SaleDetail saleDetail = new SaleDetail();
        saleDetail.setId(0);
        saleDetail.setProduct("product");
        saleDetail.setQuantity("quantity");
        saleDetail.setPrice("price");
        saleDetail.setSale(new Sale());
        sale.setSaleDetails(List.of(saleDetail));
        staff1.setSales(List.of(sale));
        final List<Staff> staff = List.of(staff1);
        when(mockStaffRepository.findAll()).thenReturn(staff);

        // Run the test
        final List<Staff> result = staffControllerUnderTest.getStaffs();

        // Verify the results
    }

    @Test
    public void testGetStaffs_StaffRepositoryReturnsNoItems() {
        // Setup
        when(mockStaffRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Staff> result = staffControllerUnderTest.getStaffs();

        // Verify the results
    }

    @Test
    public void testGetStaffById() {
        // Setup

        // Configure StaffRepository.findById(...).
        final Staff staff1 = new Staff();
        staff1.setId(0);
        staff1.setName("name");
        staff1.setAddress("address");
        staff1.setPhoneNumber("phoneNumber");
        staff1.setEmail("email");
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
        staff1.setOrder(List.of(order));
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
        sale.setStaff(new Staff());
        final SaleDetail saleDetail = new SaleDetail();
        saleDetail.setId(0);
        saleDetail.setProduct("product");
        saleDetail.setQuantity("quantity");
        saleDetail.setPrice("price");
        saleDetail.setSale(new Sale());
        sale.setSaleDetails(List.of(saleDetail));
        staff1.setSales(List.of(sale));
        final Optional<Staff> staff = Optional.of(staff1);
        when(mockStaffRepository.findById(0)).thenReturn(staff);

        // Run the test
        final Object result = staffControllerUnderTest.getStaffById(0);

        // Verify the results
    }

    @Test
    public void testGetStaffById_StaffRepositoryReturnsAbsent() {
        // Setup
        when(mockStaffRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Object result = staffControllerUnderTest.getStaffById(0);

        // Verify the results
    }

    @Test
    public void testCreateStaff() {
        // Setup
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
        sale.setStaff(new Staff());
        final SaleDetail saleDetail = new SaleDetail();
        saleDetail.setId(0);
        saleDetail.setProduct("product");
        saleDetail.setQuantity("quantity");
        saleDetail.setPrice("price");
        saleDetail.setSale(new Sale());
        sale.setSaleDetails(List.of(saleDetail));
        staff.setSales(List.of(sale));

        // Configure StaffRepository.save(...).
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
        sale1.setStaff(new Staff());
        final SaleDetail saleDetail1 = new SaleDetail();
        saleDetail1.setId(0);
        saleDetail1.setProduct("product");
        saleDetail1.setQuantity("quantity");
        saleDetail1.setPrice("price");
        saleDetail1.setSale(new Sale());
        sale1.setSaleDetails(List.of(saleDetail1));
        staff1.setSales(List.of(sale1));
        when(mockStaffRepository.save(any(Staff.class))).thenReturn(staff1);

        // Run the test
        final Object result = staffControllerUnderTest.createStaff(staff);

        // Verify the results
    }

    @Test
    public void testUpdateStaff() {
        // Setup
        final Staff newStaff = new Staff();
        newStaff.setId(0);
        newStaff.setName("name");
        newStaff.setAddress("address");
        newStaff.setPhoneNumber("phoneNumber");
        newStaff.setEmail("email");
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
        newStaff.setOrder(List.of(order));
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
        sale.setStaff(new Staff());
        final SaleDetail saleDetail = new SaleDetail();
        saleDetail.setId(0);
        saleDetail.setProduct("product");
        saleDetail.setQuantity("quantity");
        saleDetail.setPrice("price");
        saleDetail.setSale(new Sale());
        sale.setSaleDetails(List.of(saleDetail));
        newStaff.setSales(List.of(sale));

        // Configure StaffRepository.findById(...).
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
        sale1.setStaff(new Staff());
        final SaleDetail saleDetail1 = new SaleDetail();
        saleDetail1.setId(0);
        saleDetail1.setProduct("product");
        saleDetail1.setQuantity("quantity");
        saleDetail1.setPrice("price");
        saleDetail1.setSale(new Sale());
        sale1.setSaleDetails(List.of(saleDetail1));
        staff1.setSales(List.of(sale1));
        final Optional<Staff> staff = Optional.of(staff1);
        when(mockStaffRepository.findById(0)).thenReturn(staff);

        // Configure StaffRepository.save(...).
        final Staff staff2 = new Staff();
        staff2.setId(0);
        staff2.setName("name");
        staff2.setAddress("address");
        staff2.setPhoneNumber("phoneNumber");
        staff2.setEmail("email");
        final Order order2 = new Order();
        order2.setId(0);
        order2.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        order2.setStaff(new Staff());
        final Provider provider2 = new Provider();
        provider2.setId(0);
        provider2.setName("name");
        provider2.setAddress("address");
        provider2.setPhoneNumber("phoneNumber");
        provider2.setFax("fax");
        provider2.setEmail("email");
        provider2.setContactPerson("contactPerson");
        provider2.setOrder(List.of(new Order()));
        order2.setProvider(provider2);
        final OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setId(0);
        orderDetail2.setProduct("product");
        orderDetail2.setQuantity(0);
        orderDetail2.setPrice("price");
        orderDetail2.setOrder(new Order());
        order2.setOrderDetails(List.of(orderDetail2));
        staff2.setOrder(List.of(order2));
        final Sale sale2 = new Sale();
        sale2.setId(0);
        sale2.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        sale2.setTotalPrice("totalPrice");
        final Customer customer2 = new Customer();
        customer2.setId(0);
        customer2.setName("name");
        customer2.setAddress("address");
        customer2.setPhoneNumber("phoneNumber");
        customer2.setFax("fax");
        customer2.setEmail("email");
        customer2.setContactPerson("contactPerson");
        customer2.setSales(List.of(new Sale()));
        sale2.setCustomer(customer2);
        sale2.setStaff(new Staff());
        final SaleDetail saleDetail2 = new SaleDetail();
        saleDetail2.setId(0);
        saleDetail2.setProduct("product");
        saleDetail2.setQuantity("quantity");
        saleDetail2.setPrice("price");
        saleDetail2.setSale(new Sale());
        sale2.setSaleDetails(List.of(saleDetail2));
        staff2.setSales(List.of(sale2));
        when(mockStaffRepository.save(any(Staff.class))).thenReturn(staff2);

        // Run the test
        final Object result = staffControllerUnderTest.updateStaff(newStaff, 0);

        // Verify the results
    }

    @Test
    public void testUpdateStaff_StaffRepositoryFindByIdReturnsAbsent() {
        // Setup
        final Staff newStaff = new Staff();
        newStaff.setId(0);
        newStaff.setName("name");
        newStaff.setAddress("address");
        newStaff.setPhoneNumber("phoneNumber");
        newStaff.setEmail("email");
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
        newStaff.setOrder(List.of(order));
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
        sale.setStaff(new Staff());
        final SaleDetail saleDetail = new SaleDetail();
        saleDetail.setId(0);
        saleDetail.setProduct("product");
        saleDetail.setQuantity("quantity");
        saleDetail.setPrice("price");
        saleDetail.setSale(new Sale());
        sale.setSaleDetails(List.of(saleDetail));
        newStaff.setSales(List.of(sale));

        when(mockStaffRepository.findById(0)).thenReturn(Optional.empty());

        // Configure StaffRepository.save(...).
        final Staff staff = new Staff();
        staff.setId(0);
        staff.setName("name");
        staff.setAddress("address");
        staff.setPhoneNumber("phoneNumber");
        staff.setEmail("email");
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
        staff.setOrder(List.of(order1));
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
        sale1.setStaff(new Staff());
        final SaleDetail saleDetail1 = new SaleDetail();
        saleDetail1.setId(0);
        saleDetail1.setProduct("product");
        saleDetail1.setQuantity("quantity");
        saleDetail1.setPrice("price");
        saleDetail1.setSale(new Sale());
        sale1.setSaleDetails(List.of(saleDetail1));
        staff.setSales(List.of(sale1));
        when(mockStaffRepository.save(any(Staff.class))).thenReturn(staff);

        // Run the test
        final Object result = staffControllerUnderTest.updateStaff(newStaff, 0);

        // Verify the results
    }

    @Test
    public void testDeleteStaff() {
        // Setup

        // Configure StaffRepository.findById(...).
        final Staff staff1 = new Staff();
        staff1.setId(0);
        staff1.setName("name");
        staff1.setAddress("address");
        staff1.setPhoneNumber("phoneNumber");
        staff1.setEmail("email");
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
        staff1.setOrder(List.of(order));
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
        sale.setStaff(new Staff());
        final SaleDetail saleDetail = new SaleDetail();
        saleDetail.setId(0);
        saleDetail.setProduct("product");
        saleDetail.setQuantity("quantity");
        saleDetail.setPrice("price");
        saleDetail.setSale(new Sale());
        sale.setSaleDetails(List.of(saleDetail));
        staff1.setSales(List.of(sale));
        final Optional<Staff> staff = Optional.of(staff1);
        when(mockStaffRepository.findById(0)).thenReturn(staff);

        // Run the test
        staffControllerUnderTest.deleteStaff(0);

        // Verify the results
        verify(mockStaffRepository).delete(any(Staff.class));
    }

    @Test
    public void testDeleteStaff_StaffRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockStaffRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        staffControllerUnderTest.deleteStaff(0);

        // Verify the results
        verify(mockStaffRepository).delete(any(Staff.class));
    }
}
