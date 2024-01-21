package com.api.module.product.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tblPart")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "partId")
    private UUID partId;

    @Column(name = "partName", length = 1000)
    private String partName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "priceLast")
    private BigDecimal priceLast;

    @ManyToOne
    @JoinColumn(name = "partTypeId")
    private PartType partType;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "location", length = 1000)
    private String location;

    @Column(name = "inventoryQuantity")
    private int inventoryQuantity;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier supplier;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "status")
    private String status;

    public void updateFrom(Part other) {
        this.partName = other.partName;
        this.price = other.price;
        this.priceLast = other.priceLast;
        this.partType = other.partType;
        this.description = other.description;
        this.location = other.location;
        this.inventoryQuantity = other.inventoryQuantity;
        this.supplier = other.supplier;
        this.fileName = other.fileName;
        this.status = other.status;
    }
}
