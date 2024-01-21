package com.api.module.order.repositories;

import com.api.module.order.models.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderStateRepository extends JpaRepository<OrderState, UUID> {
}
