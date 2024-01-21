package com.api.module.infoweb.repositories;

import com.api.module.infoweb.models.Tc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TcRepository extends JpaRepository<Tc, UUID> {
}
