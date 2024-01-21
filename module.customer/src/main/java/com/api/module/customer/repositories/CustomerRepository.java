package com.api.module.customer.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.module.customer.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

	Optional<Customer> findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByPhone(String phone);

	boolean existsByEmail(String email);
}
