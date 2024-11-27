package com.philips.retailshop.service.impl;

import com.philips.retailshop.dto.SaleDTO;
import com.philips.retailshop.dto.SaleItemDTO;
import com.philips.retailshop.exception.ResourceNotFoundException;
import com.philips.retailshop.model.SaleEntity;
import com.philips.retailshop.repository.SaleRepository;
import com.philips.retailshop.service.SaleService;
import com.philips.retailshop.service.mapper.SaleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository repository;
    private final SaleMapper mapper;

    public SaleServiceImpl(SaleRepository repository, SaleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SaleDTO create(SaleDTO dto) {
        double totalAmount = dto.getItems().stream()
                .mapToDouble(SaleItemDTO::getTotalPrice)
                .sum();
        dto.setTotalAmount(totalAmount);

        SaleEntity entity = mapper.toEntity(dto);
        SaleEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public SaleDTO getById(Long id) throws Exception {
        SaleEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));
        return mapper.toDto(entity);
    }

    @Override
    public List<SaleDTO> getAll() {
        List<SaleEntity> entities = repository.findAll();
        return entities.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SaleDTO update(Long id, SaleDTO dto) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {
        SaleEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));
        repository.delete(entity);
    }
}
