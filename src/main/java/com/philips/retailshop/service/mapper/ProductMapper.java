package com.philips.retailshop.service.mapper;

import com.philips.retailshop.dto.ProductDTO;
import com.philips.retailshop.model.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity toEntity(ProductDTO productDTO);
    ProductDTO toDto(ProductEntity productEntity);
}
