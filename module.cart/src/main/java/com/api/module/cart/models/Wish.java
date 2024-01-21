package com.api.module.cart.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tblWish")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wishId")
    private UUID wishId;

    private String user;
    private String product;
}
