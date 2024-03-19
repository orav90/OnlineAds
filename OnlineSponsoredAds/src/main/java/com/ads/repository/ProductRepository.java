package com.ads.repository;

import com.ads.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = "SELECT * FROM product p " +
            "INNER JOIN campaign_product cp ON p.serial_number = cp.product_id " +
            "INNER JOIN campaign c ON c.id = cp.campaign_id " +
            "WHERE p.category = ?1 AND c.status = 'ACTIVE' " +
            "ORDER BY c.bid DESC LIMIT 1", nativeQuery = true)
    Product getHighestBidProductByCategory(String category);

    @Query(value = "SELECT * FROM product p " +
            "INNER JOIN campaign_product cp ON p.serial_number = cp.product_id " +
            "INNER JOIN campaign c ON c.id = cp.campaign_id " +
            "WHERE c.status = 'ACTIVE' " +
            "ORDER BY c.bid DESC LIMIT 1", nativeQuery = true)
    Product getHighestBidProduct();
}
