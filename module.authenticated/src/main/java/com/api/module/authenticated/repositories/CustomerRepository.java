package com.api.module.authenticated.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.module.authenticated.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

	Optional<Customer> findByUsername(String username);
}
