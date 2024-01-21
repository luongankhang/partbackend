package com.api.module.cart.repositories;

import com.api.module.cart.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WishRepository extends JpaRepository<Wish, UUID> {
    List<Wish> findByUser(String user);
    void deleteByUser(String user);
    void deleteByUserAndProduct(String user, String product);
}
