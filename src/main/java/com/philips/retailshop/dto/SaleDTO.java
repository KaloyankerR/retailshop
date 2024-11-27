package com.philips.retailshop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
public class SaleDTO {
    private Long saleId;

    @NotNull(message = "Customer ID is mandatory")
    private Long customerId;

    @NotNull(message = "Shop ID is mandatory")
    private Long shopId;

    @NotNull(message = "Sale date is mandatory")
    private LocalDateTime saleDate;

    private Double totalAmount;

    @NotNull(message = "Sale items are mandatory")
    private List<SaleItemDTO> items;
}
