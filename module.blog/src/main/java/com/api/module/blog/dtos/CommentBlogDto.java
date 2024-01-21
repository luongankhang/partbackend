package com.api.module.blog.dtos;

import com.api.module.blog.models.Blog;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentBlogDto {
    private UUID cmtBlogId;
    private Blog blog;
    private String user;
    private LocalDate publishedDate;
    private String content;
}
