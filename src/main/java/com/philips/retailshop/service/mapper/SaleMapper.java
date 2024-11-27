package com.philips.retailshop.service.mapper;

import com.philips.retailshop.dto.SaleDTO;
import com.philips.retailshop.model.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mapping(target = "customer.customerId", source = "customerId")
    @Mapping(target = "shop.shopId", source = "shopId")
    SaleEntity toEntity(SaleDTO saleDTO);

    @Mapping(target = "customerId", source = "customer.customerId")
    @Mapping(target = "shopId", source = "shop.shopId")
    SaleDTO toDto(SaleEntity saleEntity);
}
