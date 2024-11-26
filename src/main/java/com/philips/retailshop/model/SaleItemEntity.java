package com.philips.retailshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SaleItem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SaleItemID")
    private Long saleItemId;

    @ManyToOne
    @JoinColumn(name = "SaleID", nullable = false)
    private SaleEntity sale;

    @ManyToOne
    @JoinColumn(name = "ProductID", nullable = false)
    private ProductEntity product;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "UnitPrice", nullable = false)
    private Double unitPrice;

    @Column(name = "TotalPrice", nullable = false)
    private Double totalPrice;
}