package com.philips.retailshop.service.impl;

import com.philips.retailshop.dto.InventoryDTO;
import com.philips.retailshop.exception.ResourceNotFoundException;
import com.philips.retailshop.model.InventoryEntity;
import com.philips.retailshop.model.ProductEntity;
import com.philips.retailshop.model.ShopEntity;
import com.philips.retailshop.repository.InventoryRepository;
import com.philips.retailshop.repository.ProductRepository;
import com.philips.retailshop.repository.ShopRepository;
import com.philips.retailshop.service.InventoryService;
import com.philips.retailshop.service.mapper.InventoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private final InventoryMapper mapper;

    public InventoryServiceImpl(InventoryRepository repository, InventoryMapper mapper, ProductRepository productRepository,
                                ShopRepository shopRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
        this.mapper = mapper;
    }

    @Override
    public InventoryDTO create(InventoryDTO dto) {
        InventoryEntity entity = mapper.toEntity(dto);
        InventoryEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public InventoryDTO getById(Long id) throws Exception {
        InventoryEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found"));
        return mapper.toDto(entity);
    }

    @Override
    public List<InventoryDTO> getAll() {
        List<InventoryEntity> entities = repository.findAll();
        return entities.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public InventoryDTO update(Long id, InventoryDTO dto) throws Exception {
        InventoryEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found"));

        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        ShopEntity shop = shopRepository.findById(dto.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));

        entity.setProduct(product);
        entity.setShop(shop);
        entity.setQuantity(dto.getQuantity());

        InventoryEntity updatedEntity = repository.save(entity);
        return mapper.toDto(updatedEntity);
    }

    @Override
    public void delete(Long id) throws Exception {
        InventoryEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found"));
        repository.delete(entity);
    }
}
