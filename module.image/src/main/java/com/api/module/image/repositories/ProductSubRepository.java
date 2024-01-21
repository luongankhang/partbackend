package com.api.module.image.repositories;

import com.api.module.image.models.ProductSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductSubRepository extends JpaRepository<ProductSub, UUID> {
    List<ProductSub> findByImageProductSubId(UUID imageProductSubId);
}
