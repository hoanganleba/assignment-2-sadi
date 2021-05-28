package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.InventoryReceive;
import com.example.assignment2sadi.model.InventoryReceiveDetail;
import com.example.assignment2sadi.repository.InventoryReceiveRepository;
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
@WebMvcTest(InventoryReceiveController.class)
public class InventoryReceiveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryReceiveRepository mockInventoryReceiveRepository;

    @Test
    public void testGetInventoryReceives() throws Exception {
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
        final MockHttpServletResponse response = mockMvc.perform(get("/inventoryReceives")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testGetInventoryReceives_InventoryReceiveRepositoryReturnsNoItems() throws Exception {
        // Setup
        when(mockInventoryReceiveRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/inventoryReceives")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testGetInventoryReceiveById() throws Exception {
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
        final MockHttpServletResponse response = mockMvc.perform(get("/inventoryReceive/{inventoryReceiveId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testGetInventoryReceiveById_InventoryReceiveRepositoryReturnsAbsent() throws Exception {
        // Setup
        when(mockInventoryReceiveRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/inventoryReceive/{inventoryReceiveId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testCreateInventoryReceive() throws Exception {
        // Setup

        // Configure InventoryReceiveRepository.save(...).
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
        when(mockInventoryReceiveRepository.save(any(InventoryReceive.class))).thenReturn(inventoryReceive);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/inventoryReceive")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testUpdateInventoryReceive() throws Exception {
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

        // Configure InventoryReceiveRepository.save(...).
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
        when(mockInventoryReceiveRepository.save(any(InventoryReceive.class))).thenReturn(inventoryReceive2);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/inventoryReceive/{inventoryReceiveId}", 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testUpdateInventoryReceive_InventoryReceiveRepositoryFindByIdReturnsAbsent() throws Exception {
        // Setup
        when(mockInventoryReceiveRepository.findById(0)).thenReturn(Optional.empty());

        // Configure InventoryReceiveRepository.save(...).
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
        when(mockInventoryReceiveRepository.save(any(InventoryReceive.class))).thenReturn(inventoryReceive);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/inventoryReceive/{inventoryReceiveId}", 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testDeleteInventoryReceive() throws Exception {
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
        final MockHttpServletResponse response = mockMvc.perform(delete("/inventoryReceive/{inventoryReceiveId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockInventoryReceiveRepository).delete(any(InventoryReceive.class));
    }

    @Test
    public void testDeleteInventoryReceive_InventoryReceiveRepositoryFindByIdReturnsAbsent() throws Exception {
        // Setup
        when(mockInventoryReceiveRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/inventoryReceive/{inventoryReceiveId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockInventoryReceiveRepository).delete(any(InventoryReceive.class));
    }
}
