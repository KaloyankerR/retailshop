package com.philips.retailshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDTO {
    private Long productId;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    private String description;

    @NotNull(message = "Price is mandatory")
    private Double price;
}
