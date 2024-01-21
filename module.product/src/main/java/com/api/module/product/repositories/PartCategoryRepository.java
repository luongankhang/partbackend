package com.api.module.product.repositories;

import com.api.module.product.models.PartCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartCategoryRepository extends JpaRepository<PartCategory, UUID> {

    boolean existsByCategoryName(String categoryName);
}
