package com.api.module.infoweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tblBannerCategory")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bannerCategoryId")
    private UUID bannerCategoryId;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "hide")
    private int hide;
}
