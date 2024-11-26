package com.philips.retailshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Shop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShopID")
    private Long shopId;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Column(name = "Address", length = 255)
    private String address;

    @Column(name = "PhoneNumber", length = 20)
    private String phoneNumber;
}
