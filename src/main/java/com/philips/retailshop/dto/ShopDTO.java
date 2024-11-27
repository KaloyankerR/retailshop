package com.philips.retailshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ShopDTO {
    private Long shopId;

    @NotBlank(message = "Shop name is mandatory")
    @Size(max = 100, message = "Shop name must be less than 100 characters")
    private String name;

    @Size(max = 255, message = "Address must be less than 255 characters")
    private String address;

    @Size(max = 20, message = "Phone number must be less than 20 characters")
    private String phoneNumber;
}
