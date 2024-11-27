package com.philips.retailshop.service.mapper;

import com.philips.retailshop.dto.CustomerDTO;
import com.philips.retailshop.model.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerEntity toEntity(CustomerDTO customerDTO);
    CustomerDTO toDto(CustomerEntity customerEntity);
}
