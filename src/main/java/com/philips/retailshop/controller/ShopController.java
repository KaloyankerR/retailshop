package com.philips.retailshop.controller;

import com.philips.retailshop.dto.ShopDTO;
import com.philips.retailshop.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    private final ShopService service;

    public ShopController(ShopService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createShop(@Valid @RequestBody ShopDTO shop) {
        try {
            ShopDTO createdShop = service.create(shop);
            return new ResponseEntity<>(createdShop, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create shop: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShopById(@PathVariable("id") Long shopId) {
        try {
            ShopDTO shop = service.getById(shopId);
            return new ResponseEntity<>(shop, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Shop not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllShops() {
        try {
            List<ShopDTO> shops = service.getAll();
            return new ResponseEntity<>(shops, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch shops: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateShop(@PathVariable("id") Long shopId, @RequestBody ShopDTO shopDetails) {
        try {
            ShopDTO updatedShop = service.update(shopId, shopDetails);
            return new ResponseEntity<>(updatedShop, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update shop: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShop(@PathVariable("id") Long shopId) {
        try {
            service.delete(shopId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete shop: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
