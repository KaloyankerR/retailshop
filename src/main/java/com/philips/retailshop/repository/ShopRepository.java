package com.philips.retailshop.repository;

import com.philips.retailshop.model.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
}
