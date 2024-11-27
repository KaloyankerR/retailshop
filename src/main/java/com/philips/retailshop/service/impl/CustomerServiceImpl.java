package com.philips.retailshop.service.impl;

import com.philips.retailshop.dto.CustomerDTO;
import com.philips.retailshop.exception.ResourceNotFoundException;
import com.philips.retailshop.model.CustomerEntity;
import com.philips.retailshop.repository.CustomerRepository;
import com.philips.retailshop.service.CustomerService;
import com.philips.retailshop.service.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerServiceImpl(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CustomerDTO create(CustomerDTO dto) {
        CustomerEntity entity = mapper.toEntity(dto);
        CustomerEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public CustomerDTO getById(Long id) throws Exception {
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return mapper.toDto(entity);
    }

    @Override
    public List<CustomerDTO> getAll() {

        List<CustomerEntity> entities = repository.findAll();
        return entities.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO dto) throws Exception {
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());

        CustomerEntity updatedEntity = repository.save(entity);
        return mapper.toDto(updatedEntity);
    }

    @Override
    public void delete(Long id) throws Exception {
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        repository.delete(entity);
    }
}
