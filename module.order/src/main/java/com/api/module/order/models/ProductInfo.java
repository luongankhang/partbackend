package com.api.module.order.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tblProductInfo")
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String productName;
    private int quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_request_id")
    private Order orderRequest;
}
