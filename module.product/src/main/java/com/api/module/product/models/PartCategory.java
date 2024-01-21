package com.api.module.product.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tblPartCategory")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categoryId")
    private UUID categoryId;

    @Column(name = "categoryName")
    private String categoryName;

    public void updateFrom(PartCategory other) {
        this.categoryName = other.categoryName;
    }
}
