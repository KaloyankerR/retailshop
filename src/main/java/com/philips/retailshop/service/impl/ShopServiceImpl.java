package com.philips.retailshop.service.impl;

import com.philips.retailshop.dto.ShopDTO;
import com.philips.retailshop.exception.ResourceNotFoundException;
import com.philips.retailshop.model.ShopEntity;
import com.philips.retailshop.repository.ShopRepository;
import com.philips.retailshop.service.ShopService;
import com.philips.retailshop.service.mapper.ShopMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository repository;
    private final ShopMapper mapper;

    public ShopServiceImpl(ShopRepository repository, ShopMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ShopDTO create(ShopDTO dto) {
        ShopEntity entity = mapper.toEntity(dto);
        ShopEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public ShopDTO getById(Long id) throws Exception {
        ShopEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));
        return mapper.toDto(entity);
    }

    @Override
    public List<ShopDTO> getAll() {
        List<ShopEntity> entities = repository.findAll();
        return entities.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ShopDTO update(Long id, ShopDTO dto) throws Exception {
        ShopEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));

        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());

        ShopEntity updatedEntity = repository.save(entity);
        return mapper.toDto(updatedEntity);
    }

    @Override
    public void delete(Long id) throws Exception {
        ShopEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));
        repository.delete(entity);
    }
}
