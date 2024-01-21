package com.api.module.infoweb.repositories;

import com.api.module.infoweb.models.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AboutRepository extends JpaRepository<About, UUID> {
}
