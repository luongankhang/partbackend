package com.api.module.infoweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tblFaq")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "faqId")
    private UUID faqId;

    @Column(name = "content", length = 1000)
    private String content;

    @Column(name = "subContent", length = 1000)
    private String subContent;
}
