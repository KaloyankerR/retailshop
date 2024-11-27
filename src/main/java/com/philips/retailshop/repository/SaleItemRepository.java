package com.philips.retailshop.repository;

import com.philips.retailshop.model.SaleItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItemEntity, Long> {
}
