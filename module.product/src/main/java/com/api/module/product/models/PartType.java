package com.api.module.product.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tblPartType")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "partTypeId")
    private UUID partTypeId;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private PartCategory partCategory;

    @Column(name = "partTypeName")
    private String partTypeName;

    public void updateFrom(PartType other) {
        this.partTypeName = other.partTypeName;
        this.partCategory = other.partCategory;
    }
}
