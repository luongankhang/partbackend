package com.api.module.infoweb.repositories;

import com.api.module.infoweb.models.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BannerRepository extends JpaRepository<Banner, UUID> {
}
