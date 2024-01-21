package com.api.module.image.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tblProduct")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "imageProductId")
    private UUID imageProductId;

    @Column(name = "fileName")
    private String fileName;

    public Product(String fileName) {
        this.fileName = fileName;
    }
}
