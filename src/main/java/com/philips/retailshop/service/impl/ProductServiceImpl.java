package com.philips.retailshop.service.impl;

import com.philips.retailshop.dto.ProductDTO;
import com.philips.retailshop.exception.ResourceNotFoundException;
import com.philips.retailshop.model.ProductEntity;
import com.philips.retailshop.repository.ProductRepository;
import com.philips.retailshop.service.ProductService;
import com.philips.retailshop.service.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        ProductEntity entity = mapper.toEntity(productDTO);
        ProductEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public ProductDTO getById(Long productId) {
        ProductEntity entity = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return mapper.toDto(entity);
    }

    @Override
    public List<ProductDTO> getAll() {
        List<ProductEntity> entities = repository.findAll();
        return entities.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDTO update(Long productId, ProductDTO productDTO) {
        ProductEntity entity = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        entity.setName(productDTO.getName());
        entity.setDescription(productDTO.getDescription());
        entity.setPrice(productDTO.getPrice());
        ProductEntity updatedEntity = repository.save(entity);
        return mapper.toDto(updatedEntity);
    }

    @Override
    public void delete(Long productId) {
        ProductEntity entity = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        repository.delete(entity);
    }
}