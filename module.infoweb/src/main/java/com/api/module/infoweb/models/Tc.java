package com.api.module.infoweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tblTc")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tcId")
    private UUID tcId;

    @Column(name = "content", length = 1000)
    private String content;
}
