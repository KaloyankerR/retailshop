package com.philips.retailshop.service;

import com.philips.retailshop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO product);
    ProductDTO getProductById(Long productId) throws Exception;
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(Long productId, ProductDTO product) throws Exception;
    void deleteProduct(Long productId) throws Exception;
}
