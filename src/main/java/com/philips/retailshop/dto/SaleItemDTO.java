package com.philips.retailshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaleItemDTO {
    private Long saleItemId;

    @NotNull(message = "Sale ID is mandatory")
    private Long saleId;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Unit price is mandatory")
    @Min(value = 0, message = "Unit price must be at least 0")
    private Double unitPrice;

    @NotNull(message = "Total price is mandatory")
    @Min(value = 0, message = "Total price must be at least 0")
    private Double totalPrice;
}

