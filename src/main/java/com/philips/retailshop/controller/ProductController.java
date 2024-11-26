package com.philips.retailshop.controller;

import com.philips.retailshop.dto.ProductDTO;
import com.philips.retailshop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO product) {
        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable("id") Long productId) throws Exception {
        return productService.getProductById(productId);
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable("id") Long productId, @RequestBody ProductDTO productDetails) throws Exception {
        return productService.updateProduct(productId, productDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) throws Exception {
        productService.deleteProduct(productId);
    }
}