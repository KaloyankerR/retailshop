package com.philips.retailshop.controller;

import com.philips.retailshop.dto.CustomerDTO;
import com.philips.retailshop.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer() {
        CustomerDTO mockCustomer = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com", "1234567890", "123 Main St");
        when(customerService.create(any(CustomerDTO.class))).thenReturn(mockCustomer);

        ResponseEntity<?> response = customerController.createCustomer(mockCustomer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockCustomer, response.getBody());
        verify(customerService, times(1)).create(mockCustomer);
    }

    @Test
    void testGetCustomerById() throws Exception {
        CustomerDTO mockCustomer = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com", "1234567890", "123 Main St");
        when(customerService.getById(1L)).thenReturn(mockCustomer);

        ResponseEntity<?> response = customerController.getCustomerById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCustomer, response.getBody());
        verify(customerService, times(1)).getById(1L);
    }

    @Test
    void testGetCustomerById_NotFound() throws Exception {
        when(customerService.getById(1L)).thenThrow(new RuntimeException("Customer not found"));

        ResponseEntity<?> response = customerController.getCustomerById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Customer not found"));
        verify(customerService, times(1)).getById(1L);
    }

    @Test
    void testGetAllCustomers() {
        List<CustomerDTO> mockCustomers = Arrays.asList(
                new CustomerDTO(1L, "John", "Doe", "john.doe@example.com", "1234567890", "123 Main St"),
                new CustomerDTO(2L, "Jane", "Smith", "jane.smith@example.com", "9876543210", "456 Elm St")
        );
        when(customerService.getAll()).thenReturn(mockCustomers);

        ResponseEntity<?> response = customerController.getAllCustomers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCustomers, response.getBody());
        verify(customerService, times(1)).getAll();
    }

    @Test
    void testUpdateCustomer() throws Exception {
        CustomerDTO mockCustomer = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com", "1234567890", "123 Main St");
        when(customerService.update(eq(1L), any(CustomerDTO.class))).thenReturn(mockCustomer);

        ResponseEntity<?> response = customerController.updateCustomer(1L, mockCustomer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCustomer, response.getBody());
        verify(customerService, times(1)).update(1L, mockCustomer);
    }

    @Test
    void testDeleteCustomer() throws Exception {
        doNothing().when(customerService).delete(1L);

        ResponseEntity<?> response = customerController.deleteCustomer(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerService, times(1)).delete(1L);
    }

    @Test
    void testDeleteCustomer_Failure() throws Exception {
        doThrow(new RuntimeException("Failed to delete customer")).when(customerService).delete(1L);

        ResponseEntity<?> response = customerController.deleteCustomer(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Failed to delete customer"));
        verify(customerService, times(1)).delete(1L);
    }
}