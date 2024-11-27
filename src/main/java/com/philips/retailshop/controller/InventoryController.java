package com.philips.retailshop.controller;

import com.philips.retailshop.dto.InventoryDTO;
import com.philips.retailshop.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createInventory(@Valid @RequestBody InventoryDTO inventory) {
        try {
            InventoryDTO createdInventory = service.create(inventory);
            return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create inventory: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInventoryById(@PathVariable("id") Long inventoryId) {
        try {
            InventoryDTO inventory = service.getById(inventoryId);
            return new ResponseEntity<>(inventory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Inventory not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllInventory() {
        try {
            List<InventoryDTO> inventories = service.getAll();
            return new ResponseEntity<>(inventories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch inventory: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInventory(@PathVariable("id") Long inventoryId, @RequestBody InventoryDTO inventoryDetails) {
        try {
            InventoryDTO updatedInventory = service.update(inventoryId, inventoryDetails);
            return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update inventory: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable("id") Long inventoryId) {
        try {
            service.delete(inventoryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete inventory: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
