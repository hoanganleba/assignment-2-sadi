package com.example.assignment2sadi.controller;

import com.example.assignment2sadi.model.InventoryReceive;
import com.example.assignment2sadi.model.InventoryReceiveDetail;
import com.example.assignment2sadi.repository.InventoryReceiveDetailRepository;
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
@WebMvcTest(InventoryReceiveDetailController.class)
public class InventoryReceiveDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryReceiveDetailRepository mockInventoryReceiveDetailRepository;
    @MockBean
    private InventoryReceiveRepository mockInventoryReceiveRepository;

    @Test
    public void testGetInventoryReceiveDetails() throws Exception {
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
        final MockHttpServletResponse response = mockMvc.perform(get("/inventoryReceiveDetails")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testGetInventoryReceiveDetails_InventoryReceiveDetailRepositoryReturnsNoItems() throws Exception {
        // Setup
        when(mockInventoryReceiveDetailRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/inventoryReceiveDetails")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testGetInventoryReceiveDetailById() throws Exception {
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
        final MockHttpServletResponse response = mockMvc.perform(get("/inventoryReceiveDetail/{inventoryReceiveDetailId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testGetInventoryReceiveDetailById_InventoryReceiveDetailRepositoryReturnsAbsent() throws Exception {
        // Setup
        when(mockInventoryReceiveDetailRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/inventoryReceiveDetail/{inventoryReceiveDetailId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testCreateInventoryReceiveDetail() throws Exception {
        // Setup

        // Configure InventoryReceiveDetailRepository.save(...).
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
        when(mockInventoryReceiveDetailRepository.save(any(InventoryReceiveDetail.class))).thenReturn(inventoryReceiveDetail);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/inventoryReceiveDetail")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testCreateReceiveDetailByReceiveId() throws Exception {
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

        // Configure InventoryReceiveDetailRepository.save(...).
        final InventoryReceiveDetail inventoryReceiveDetail1 = new InventoryReceiveDetail();
        inventoryReceiveDetail1.setId(0);
        inventoryReceiveDetail1.setProduct("product");
        inventoryReceiveDetail1.setQuantity(0);
        final InventoryReceive inventoryReceive2 = new InventoryReceive();
        inventoryReceive2.setId(0);
        inventoryReceive2.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive2.setName("name");
        inventoryReceive2.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail1.setInventoryReceive(inventoryReceive2);
        when(mockInventoryReceiveDetailRepository.save(any(InventoryReceiveDetail.class))).thenReturn(inventoryReceiveDetail1);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/inventoryReceive/{inventoryReceiveId}/inventoryReceiveDetail", 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testCreateReceiveDetailByReceiveId_InventoryReceiveRepositoryReturnsAbsent() throws Exception {
        // Setup
        when(mockInventoryReceiveRepository.findById(0)).thenReturn(Optional.empty());

        // Configure InventoryReceiveDetailRepository.save(...).
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
        when(mockInventoryReceiveDetailRepository.save(any(InventoryReceiveDetail.class))).thenReturn(inventoryReceiveDetail);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/inventoryReceive/{inventoryReceiveId}/inventoryReceiveDetail", 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testUpdateInventoryReceiveDetail() throws Exception {
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

        // Configure InventoryReceiveDetailRepository.save(...).
        final InventoryReceiveDetail inventoryReceiveDetail2 = new InventoryReceiveDetail();
        inventoryReceiveDetail2.setId(0);
        inventoryReceiveDetail2.setProduct("product");
        inventoryReceiveDetail2.setQuantity(0);
        final InventoryReceive inventoryReceive1 = new InventoryReceive();
        inventoryReceive1.setId(0);
        inventoryReceive1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        inventoryReceive1.setName("name");
        inventoryReceive1.setInventoryReceiveDetails(List.of(new InventoryReceiveDetail()));
        inventoryReceiveDetail2.setInventoryReceive(inventoryReceive1);
        when(mockInventoryReceiveDetailRepository.save(any(InventoryReceiveDetail.class))).thenReturn(inventoryReceiveDetail2);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/inventoryReceiveDetail/{inventoryReceiveDetailId}", 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testUpdateInventoryReceiveDetail_InventoryReceiveDetailRepositoryFindByIdReturnsAbsent() throws Exception {
        // Setup
        when(mockInventoryReceiveDetailRepository.findById(0)).thenReturn(Optional.empty());

        // Configure InventoryReceiveDetailRepository.save(...).
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
        when(mockInventoryReceiveDetailRepository.save(any(InventoryReceiveDetail.class))).thenReturn(inventoryReceiveDetail);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/inventoryReceiveDetail/{inventoryReceiveDetailId}", 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    public void testDeleteInventoryReceiveDetail() throws Exception {
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
        final MockHttpServletResponse response = mockMvc.perform(delete("/inventoryReceiveDetail/{inventoryReceiveDetailId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockInventoryReceiveDetailRepository).delete(any(InventoryReceiveDetail.class));
    }

    @Test
    public void testDeleteInventoryReceiveDetail_InventoryReceiveDetailRepositoryFindByIdReturnsAbsent() throws Exception {
        // Setup
        when(mockInventoryReceiveDetailRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/inventoryReceiveDetail/{inventoryReceiveDetailId}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockInventoryReceiveDetailRepository).delete(any(InventoryReceiveDetail.class));
    }
}
