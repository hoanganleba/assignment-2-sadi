package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.*;
import com.example.assignment2sadi.repository.InventoryDeliveryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(MockitoJUnitRunner.class)
public class InventoryDeliveryControllerTest {
    @Mock
    private InventoryDeliveryRepository mockInventoryDeliveryRepository;

    private InventoryDeliveryController inventoryDeliveryControllerUnderTest;

    @Before
    public void setUp() throws Exception {
        inventoryDeliveryControllerUnderTest = new InventoryDeliveryController(mockInventoryDeliveryRepository);
    }

    @Test
    public void testCreateInventoryDelivery() {
        // Setup
        final InventoryDelivery inventoryDelivery = new InventoryDelivery();
        inventoryDelivery.setId(0);
        inventoryDelivery.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
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
        inventoryDelivery.setStaff(staff);
        final InventoryDeliveryDetail inventoryDeliveryDetail = new InventoryDeliveryDetail();
        inventoryDeliveryDetail.setId(0);
        inventoryDeliveryDetail.setProduct("product");
        inventoryDeliveryDetail.setQuantity(0);
        inventoryDeliveryDetail.setInventoryDelivery(new InventoryDelivery());
        inventoryDelivery.setInventoryDeliveryDetail(List.of(inventoryDeliveryDetail));

        // Run the test
        final Object result = inventoryDeliveryControllerUnderTest.createInventoryDelivery(inventoryDelivery);

        // Verify the results
    }

    @Test
    public void testDeleteInventoryDelivery() {
        // Setup

        // Run the test
        inventoryDeliveryControllerUnderTest.deleteInventoryDelivery(0);

        // Verify the results
    }

    @Test
    public void testGetInventoryDeliveries1() {
        // Setup

        // Run the test
        final List<InventoryDelivery> result = inventoryDeliveryControllerUnderTest.getInventoryDeliveries();

        // Verify the results
    }

    @Test
    public void testGetInventoryDeliveries2() throws ParseException {
        // Setup

        // Run the test
        final List<InventoryDelivery> result = inventoryDeliveryControllerUnderTest.getInventoryDeliveries("2020-03-12", "2020-03-12");

        // Verify the results
    }

    @Test
    public void testGetInventoryDeliveries2_ThrowsParseException() {
        // Setup

        // Run the test
        assertThatThrownBy(() -> inventoryDeliveryControllerUnderTest.getInventoryDeliveries("2020-03-12", "2020-03-12")).isInstanceOf(ParseException.class);
    }

    @Test
    public void testGetInventoryDeliveryById() {
        // Setup

        // Run the test
        final Object result = inventoryDeliveryControllerUnderTest.getInventoryDeliveryById(0);

        // Verify the results
    }

    @Test
    public void testGetInventoryReceives() throws ParseException {
        // Setup

        // Run the test
        final List<InventoryDelivery> result = inventoryDeliveryControllerUnderTest.getInventoryReceives("2020-03-12");

        // Verify the results
    }

    @Test
    public void testGetInventoryReceives_ThrowsParseException() {
        // Setup

        // Run the test
        assertThatThrownBy(() -> inventoryDeliveryControllerUnderTest.getInventoryReceives("2020-03-12")).isInstanceOf(ParseException.class);
    }

    @Test
    public void testUpdateInventoryDelivery() {
        // Setup
        final InventoryDelivery newInventoryDelivery = new InventoryDelivery();
        newInventoryDelivery.setId(0);
        newInventoryDelivery.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
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
        newInventoryDelivery.setStaff(staff);
        final InventoryDeliveryDetail inventoryDeliveryDetail = new InventoryDeliveryDetail();
        inventoryDeliveryDetail.setId(0);
        inventoryDeliveryDetail.setProduct("product");
        inventoryDeliveryDetail.setQuantity(0);
        inventoryDeliveryDetail.setInventoryDelivery(new InventoryDelivery());
        newInventoryDelivery.setInventoryDeliveryDetail(List.of(inventoryDeliveryDetail));

        // Run the test
        final Object result = inventoryDeliveryControllerUnderTest.updateInventoryDelivery(newInventoryDelivery, 0);

        // Verify the results
    }
}
