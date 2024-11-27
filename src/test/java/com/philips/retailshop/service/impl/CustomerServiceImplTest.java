package com.philips.retailshop.service.impl;

import com.philips.retailshop.dto.CustomerDTO;
import com.philips.retailshop.exception.ResourceNotFoundException;
import com.philips.retailshop.model.CustomerEntity;
import com.philips.retailshop.repository.CustomerRepository;
import com.philips.retailshop.service.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer() {
        CustomerDTO dto = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com", "1234567890", "123 Main St");
        CustomerEntity entity = new CustomerEntity();
        CustomerEntity savedEntity = new CustomerEntity();
        savedEntity.setCustomerId(1L);

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDto(savedEntity)).thenReturn(dto);

        CustomerDTO result = service.create(dto);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(repository, times(1)).save(entity);
    }

    @Test
    void testGetCustomerById_Success() throws Exception {
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(1L);
        entity.setFirstName("John");

        CustomerDTO dto = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com", "1234567890", "123 Main St");

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        CustomerDTO result = service.getById(1L);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetCustomerById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.getById(1L));

        assertEquals("Customer not found", exception.getMessage());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetAllCustomers() {
        CustomerEntity entity1 = new CustomerEntity();
        entity1.setCustomerId(1L);
        CustomerEntity entity2 = new CustomerEntity();
        entity2.setCustomerId(2L);

        CustomerDTO dto1 = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com", "1234567890", "123 Main St");
        CustomerDTO dto2 = new CustomerDTO(2L, "Jane", "Smith", "jane.smith@example.com", "9876543210", "456 Elm St");

        when(repository.findAll()).thenReturn(Arrays.asList(entity1, entity2));
        when(mapper.toDto(entity1)).thenReturn(dto1);
        when(mapper.toDto(entity2)).thenReturn(dto2);

        List<CustomerDTO> result = service.getAll();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testUpdateCustomer_Success() throws Exception {
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(1L);

        CustomerDTO dto = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com", "1234567890", "123 Main St");
        CustomerEntity updatedEntity = new CustomerEntity();
        updatedEntity.setCustomerId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(updatedEntity);
        when(mapper.toDto(updatedEntity)).thenReturn(dto);

        CustomerDTO result = service.update(1L, dto);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(entity);
    }

    @Test
    void testUpdateCustomer_NotFound() {
        CustomerDTO dto = new CustomerDTO();

        when(repository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.update(1L, dto));

        assertEquals("Customer not found", exception.getMessage());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testDeleteCustomer_Success() throws Exception {
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        doNothing().when(repository).delete(entity);

        service.delete(1L);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).delete(entity);
    }

    @Test
    void testDeleteCustomer_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.delete(1L));

        assertEquals("Customer not found", exception.getMessage());
        verify(repository, times(1)).findById(1L);
    }
}
