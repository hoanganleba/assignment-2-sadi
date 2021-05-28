package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.*;
import com.example.assignment2sadi.repository.InventoryDeliveryDetailRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(InventoryDeliveryDetailController.class)
public class InventoryDeliveryDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryDeliveryDetailRepository mockInventoryDeliveryDetailRepository;

    @Test
    public void testGetInventoryDeliveryDetails() throws Exception {
        // Setup

        // Configure InventoryDeliveryDetailRepository.findAll(...).
        final InventoryDeliveryDetail inventoryDeliveryDetail = new InventoryDeliveryDetail();
        inventoryDeliveryDetail.setId(0);
        inventoryDeliveryDetail.setProduct("product");
        inventoryDeliveryDetail.setQuantity(0);
        final InventoryDelivery inventoryDelivery = new InventoryDelivery();
        inventoryDelivery.setId(0);
        inventoryDelivery.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryDelivery.setName("name");
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
        inventoryDelivery.setInventoryDeliveryDetail(List.of(new InventoryDeliveryDetail()));
        inventoryDeliveryDetail.setInventoryDelivery(inventoryDelivery);
        final List<InventoryDeliveryDetail> inventoryDeliveryDetails = List.of(inventoryDeliveryDetail);
        when(mockInventoryDeliveryDetailRepository.findAll()).thenReturn(inventoryDeliveryDetails);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/inventoryDeliveryDetails")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testGetInventoryDeliveryDetails_InventoryDeliveryDetailRepositoryReturnsNoItems() throws Exception {
        // Setup
        when(mockInventoryDeliveryDetailRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/inventoryDeliveryDetails")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testGetInventoryDeliveryDetailById() throws Exception {
        // Setup

        // Configure InventoryDeliveryDetailRepository.findById(...).
        final InventoryDeliveryDetail inventoryDeliveryDetail1 = new InventoryDeliveryDetail();
        inventoryDeliveryDetail1.setId(0);
        inventoryDeliveryDetail1.setProduct("product");
        inventoryDeliveryDetail1.setQuantity(0);
        final InventoryDelivery inventoryDelivery = new InventoryDelivery();
        inventoryDelivery.setId(0);
        inventoryDelivery.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryDelivery.setName("name");
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
        inventoryDelivery.setInventoryDeliveryDetail(List.of(new InventoryDeliveryDetail()));
        inventoryDeliveryDetail1.setInventoryDelivery(inventoryDelivery);
        final Optional<InventoryDeliveryDetail> inventoryDeliveryDetail = Optional.of(inventoryDeliveryDetail1);
        when(mockInventoryDeliveryDetailRepository.findById(0)).thenReturn(inventoryDeliveryDetail);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/inventoryDeliveryDetail/{inventoryDeliveryDetailId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testGetInventoryDeliveryDetailById_InventoryDeliveryDetailRepositoryReturnsAbsent() throws Exception {
        // Setup
        when(mockInventoryDeliveryDetailRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/inventoryDeliveryDetail/{inventoryDeliveryDetailId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testCreateInventoryDeliveryDetail() throws Exception {
        // Setup

        // Configure InventoryDeliveryDetailRepository.save(...).
        final InventoryDeliveryDetail inventoryDeliveryDetail = new InventoryDeliveryDetail();
        inventoryDeliveryDetail.setId(0);
        inventoryDeliveryDetail.setProduct("product");
        inventoryDeliveryDetail.setQuantity(0);
        final InventoryDelivery inventoryDelivery = new InventoryDelivery();
        inventoryDelivery.setId(0);
        inventoryDelivery.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryDelivery.setName("name");
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
        inventoryDelivery.setInventoryDeliveryDetail(List.of(new InventoryDeliveryDetail()));
        inventoryDeliveryDetail.setInventoryDelivery(inventoryDelivery);
        when(mockInventoryDeliveryDetailRepository.save(any(InventoryDeliveryDetail.class))).thenReturn(inventoryDeliveryDetail);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/inventoryDeliveryDetail")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testUpdateInventoryDeliveryDetail() throws Exception {
        // Setup

        // Configure InventoryDeliveryDetailRepository.findById(...).
        final InventoryDeliveryDetail inventoryDeliveryDetail1 = new InventoryDeliveryDetail();
        inventoryDeliveryDetail1.setId(0);
        inventoryDeliveryDetail1.setProduct("product");
        inventoryDeliveryDetail1.setQuantity(0);
        final InventoryDelivery inventoryDelivery = new InventoryDelivery();
        inventoryDelivery.setId(0);
        inventoryDelivery.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryDelivery.setName("name");
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
        inventoryDelivery.setInventoryDeliveryDetail(List.of(new InventoryDeliveryDetail()));
        inventoryDeliveryDetail1.setInventoryDelivery(inventoryDelivery);
        final Optional<InventoryDeliveryDetail> inventoryDeliveryDetail = Optional.of(inventoryDeliveryDetail1);
        when(mockInventoryDeliveryDetailRepository.findById(0)).thenReturn(inventoryDeliveryDetail);

        // Configure InventoryDeliveryDetailRepository.save(...).
        final InventoryDeliveryDetail inventoryDeliveryDetail2 = new InventoryDeliveryDetail();
        inventoryDeliveryDetail2.setId(0);
        inventoryDeliveryDetail2.setProduct("product");
        inventoryDeliveryDetail2.setQuantity(0);
        final InventoryDelivery inventoryDelivery1 = new InventoryDelivery();
        inventoryDelivery1.setId(0);
        inventoryDelivery1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryDelivery1.setName("name");
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
        inventoryDelivery1.setStaff(staff1);
        inventoryDelivery1.setInventoryDeliveryDetail(List.of(new InventoryDeliveryDetail()));
        inventoryDeliveryDetail2.setInventoryDelivery(inventoryDelivery1);
        when(mockInventoryDeliveryDetailRepository.save(any(InventoryDeliveryDetail.class))).thenReturn(inventoryDeliveryDetail2);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/inventoryDeliveryDetail/{inventoryDeliveryDetailId}", 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testUpdateInventoryDeliveryDetail_InventoryDeliveryDetailRepositoryFindByIdReturnsAbsent() throws Exception {
        // Setup
        when(mockInventoryDeliveryDetailRepository.findById(0)).thenReturn(Optional.empty());

        // Configure InventoryDeliveryDetailRepository.save(...).
        final InventoryDeliveryDetail inventoryDeliveryDetail = new InventoryDeliveryDetail();
        inventoryDeliveryDetail.setId(0);
        inventoryDeliveryDetail.setProduct("product");
        inventoryDeliveryDetail.setQuantity(0);
        final InventoryDelivery inventoryDelivery = new InventoryDelivery();
        inventoryDelivery.setId(0);
        inventoryDelivery.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryDelivery.setName("name");
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
        inventoryDelivery.setInventoryDeliveryDetail(List.of(new InventoryDeliveryDetail()));
        inventoryDeliveryDetail.setInventoryDelivery(inventoryDelivery);
        when(mockInventoryDeliveryDetailRepository.save(any(InventoryDeliveryDetail.class))).thenReturn(inventoryDeliveryDetail);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/inventoryDeliveryDetail/{inventoryDeliveryDetailId}", 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testDeleteInventoryDeliveryDetail() throws Exception {
        // Setup

        // Configure InventoryDeliveryDetailRepository.findById(...).
        final InventoryDeliveryDetail inventoryDeliveryDetail1 = new InventoryDeliveryDetail();
        inventoryDeliveryDetail1.setId(0);
        inventoryDeliveryDetail1.setProduct("product");
        inventoryDeliveryDetail1.setQuantity(0);
        final InventoryDelivery inventoryDelivery = new InventoryDelivery();
        inventoryDelivery.setId(0);
        inventoryDelivery.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryDelivery.setName("name");
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
        inventoryDelivery.setInventoryDeliveryDetail(List.of(new InventoryDeliveryDetail()));
        inventoryDeliveryDetail1.setInventoryDelivery(inventoryDelivery);
        final Optional<InventoryDeliveryDetail> inventoryDeliveryDetail = Optional.of(inventoryDeliveryDetail1);
        when(mockInventoryDeliveryDetailRepository.findById(0)).thenReturn(inventoryDeliveryDetail);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/inventoryDeliveryDetail/{inventoryDeliveryDetailId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockInventoryDeliveryDetailRepository).delete(any(InventoryDeliveryDetail.class));
    }

    @Test
    public void testDeleteInventoryDeliveryDetail_InventoryDeliveryDetailRepositoryFindByIdReturnsAbsent() throws Exception {
        // Setup
        when(mockInventoryDeliveryDetailRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/inventoryDeliveryDetail/{inventoryDeliveryDetailId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockInventoryDeliveryDetailRepository).delete(any(InventoryDeliveryDetail.class));
    }
}
