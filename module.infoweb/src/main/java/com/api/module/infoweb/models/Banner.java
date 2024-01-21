package com.api.module.infoweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tblBanner")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bannerId")
    private UUID bannerId;

    @Column(name = "fileName1")
    private String fileName1;

    @Column(name = "fileName2")
    private String fileName2;

    @Column(name = "hide")
    private int hide;
}
