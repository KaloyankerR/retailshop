package com.philips.retailshop.service.mapper;

import com.philips.retailshop.dto.InventoryDTO;
import com.philips.retailshop.model.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    @Mapping(target = "product.productId", source = "productId")
    @Mapping(target = "product.name", source = "productName")
    @Mapping(target = "shop.shopId", source = "shopId")
    @Mapping(target = "shop.name", source = "shopName")
    InventoryEntity toEntity(InventoryDTO inventoryDTO);

    @Mapping(target = "productId", source = "product.productId")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "shopId", source = "shop.shopId")
    @Mapping(target = "shopName", source = "shop.name")
    InventoryDTO toDto(InventoryEntity inventoryEntity);
}
