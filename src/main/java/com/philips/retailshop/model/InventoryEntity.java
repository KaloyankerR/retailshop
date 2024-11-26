package com.philips.retailshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoryID")
    private Long inventoryId;

    @ManyToOne
    @JoinColumn(name = "ProductID", nullable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "ShopID", nullable = false)
    private ShopEntity shop;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;
}