package com.api.module.blog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tblBlog")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blogId")
    private UUID blogId;

    private String title;
    private String user;
    private LocalDate publishedDate;
    private String content;
    private String fileName;

    public void updateFrom(Blog other) {
        this.title = other.title;
        this.content = other.content;
        this.fileName = other.fileName;
    }
}
