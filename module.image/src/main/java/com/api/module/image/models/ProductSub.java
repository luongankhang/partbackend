package com.api.module.image.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tblProductSub")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSub {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "imageProductSubId")
    private UUID imageProductSubId;

    @ManyToOne
    @JoinColumn(name = "imageProductId")
    private Product product;

    @Column(name = "fileName1")
    private String fileName1;

    @Column(name = "fileName2")
    private String fileName2;

    @Column(name = "fileName3")
    private String fileName3;

    @Column(name = "fileName4")
    private String fileName4;

    @Column(name = "fileName5")
    private String fileName5;

    public ProductSub(List<String> fileNames) {
        while (fileNames.size() < 5) {
            fileNames.add(null);
        }

        this.fileName1 = fileNames.get(0);
        this.fileName2 = fileNames.get(1);
        this.fileName3 = fileNames.get(2);
        this.fileName4 = fileNames.get(3);
        this.fileName5 = fileNames.get(4);
    }
}
