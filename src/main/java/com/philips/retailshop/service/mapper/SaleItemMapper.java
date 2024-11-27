package com.philips.retailshop.service.mapper;

import com.philips.retailshop.dto.SaleItemDTO;
import com.philips.retailshop.model.SaleItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleItemMapper {
    @Mapping(target = "product.productId", source = "productId")
    @Mapping(target = "sale.saleId", source = "saleId")
    SaleItemEntity toEntity(SaleItemDTO saleItemDTO);

    @Mapping(target = "productId", source = "product.productId")
    @Mapping(target = "saleId", source = "sale.saleId")
    SaleItemDTO toDto(SaleItemEntity saleItemEntity);
}
