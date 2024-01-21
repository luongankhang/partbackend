package com.api.module.blog.dtos;

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
public class BlogDto {
    private UUID blogId;
    private String title;
    private String user;
    private LocalDate publishedDate;
    private String content;
    private String fileName;
}
