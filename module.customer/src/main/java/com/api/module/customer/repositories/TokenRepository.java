package com.api.module.customer.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.module.customer.models.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {

	@Query(value = """
			SELECT t FROM Token t INNER JOIN t.customer c
			WHERE c.customerId = :customerId AND (t.expired = false OR t.revoked = false)
			""")
	List<Token> findAllValidTokenByCustomer(UUID customerId);
}
