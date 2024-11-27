package com.philips.retailshop.controller;

import com.philips.retailshop.dto.SaleDTO;
import com.philips.retailshop.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createSale(@Valid @RequestBody SaleDTO sale) {
        try {
            SaleDTO createdSale = service.create(sale);
            return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create sale: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable("id") Long saleId) {
        try {
            SaleDTO sale = service.getById(saleId);
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Sale not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSales() {
        try {
            List<SaleDTO> sales = service.getAll();
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch sales: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable("id") Long saleId) {
        try {
            service.delete(saleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete sale: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
