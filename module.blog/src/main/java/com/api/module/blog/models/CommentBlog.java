package com.api.module.blog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tblCommentBlog")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cmtBlogId")
    private UUID cmtBlogId;

    @ManyToOne
    @JoinColumn(name = "blogId")
    private Blog blog;

    private String user;
    private LocalDate publishedDate;
    private String content;
}
