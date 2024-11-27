package com.philips.retailshop.controller;

import com.philips.retailshop.dto.CustomerDTO;
import com.philips.retailshop.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDTO customer) {
        try {
            CustomerDTO createdCustomer = service.create(customer);
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create customer: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long customerId) {
        try {
            CustomerDTO customer = service.getById(customerId);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Customer not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        try {
            List<CustomerDTO> customers = service.getAll();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch customers: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomerDTO customerDetails) {
        try {
            CustomerDTO updatedCustomer = service.update(customerId, customerDetails);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update customer: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long customerId) {
        try {
            service.delete(customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete customer: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
