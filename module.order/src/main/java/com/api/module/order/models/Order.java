package com.api.module.order.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tblOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String user;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderRequest")
    private List<ProductInfo> products;  // Change from 'product' to 'products'

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private OrderState orderState;

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
    private String note;
}