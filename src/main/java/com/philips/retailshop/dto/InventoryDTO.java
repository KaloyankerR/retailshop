package com.philips.retailshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryDTO {
    private Long inventoryId;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;

    private String productName;

    @NotNull(message = "Shop ID is mandatory")
    private Long shopId;

    private String shopName;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 0, message = "Quantity must be at least 0")
    private Integer quantity;
}
