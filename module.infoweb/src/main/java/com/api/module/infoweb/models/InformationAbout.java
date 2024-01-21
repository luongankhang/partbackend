package com.api.module.infoweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tblInformationAbout")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformationAbout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iAId")
    private UUID iAId;

    @Column(name = "content", length = 1000)
    private String content;

    @Column(name = "hide")
    private int hide;
}
