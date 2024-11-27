package com.philips.retailshop.service.mapper;

import com.philips.retailshop.dto.ShopDTO;
import com.philips.retailshop.model.ShopEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopMapper {
    ShopEntity toEntity(ShopDTO shopDTO);
    ShopDTO toDto(ShopEntity shopEntity);
}
