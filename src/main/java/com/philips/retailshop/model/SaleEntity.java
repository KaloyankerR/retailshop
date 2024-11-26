package com.philips.retailshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SaleID")
    private Long saleId;

    @ManyToOne
    @JoinColumn(name = "CustomerID", nullable = false)
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "ShopID", nullable = false)
    private ShopEntity shop;

    @Column(name = "SaleDate", nullable = false)
    private LocalDateTime saleDate;

    @Column(name = "TotalAmount", nullable = false)
    private Double totalAmount;
}