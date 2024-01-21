package com.api.module.authenticated.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.module.authenticated.models.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {

	Optional<Token> findByToken(String token);
}
