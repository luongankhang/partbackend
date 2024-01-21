package com.api.module.cart.repositories;

import com.api.module.cart.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    void deleteByUser(String user);
    void deleteByUserAndProduct(String user, String product);
}
