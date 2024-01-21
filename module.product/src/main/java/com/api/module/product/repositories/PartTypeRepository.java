package com.api.module.product.repositories;

import com.api.module.product.models.PartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartTypeRepository extends JpaRepository<PartType, UUID> {
    boolean existsByPartTypeName(String partTypeName);
}
