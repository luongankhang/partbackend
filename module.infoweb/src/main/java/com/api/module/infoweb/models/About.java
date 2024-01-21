package com.api.module.infoweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tblAbout")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "aboutId")
    private UUID aboutId;

    @Column(name = "content", length = 1000)
    private String content;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "hide")
    private int hide;
}
