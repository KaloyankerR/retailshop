package com.philips.retailshop.service.impl;

import com.philips.retailshop.dto.ProductDTO;
import com.philips.retailshop.exception.ResourceNotFoundException;
import com.philips.retailshop.model.ProductEntity;
import com.philips.retailshop.repository.ProductRepository;
import com.philips.retailshop.service.ProductService;
import com.philips.retailshop.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity entity = productMapper.toEntity(productDTO);
        ProductEntity savedEntity = productRepository.save(entity);
        return productMapper.toDto(savedEntity);
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        ProductEntity entity = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return productMapper.toDto(entity);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductEntity> entities = productRepository.findAll();
        return entities.stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        ProductEntity entity = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        entity.setName(productDTO.getName());
        entity.setDescription(productDTO.getDescription());
        entity.setPrice(productDTO.getPrice());
        ProductEntity updatedEntity = productRepository.save(entity);
        return productMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteProduct(Long productId) {
        ProductEntity entity = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(entity);
    }
}