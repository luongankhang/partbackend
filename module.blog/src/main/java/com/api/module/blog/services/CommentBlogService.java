package com.api.module.blog.services;

import com.api.module.blog.dtos.CommentBlogDto;
import com.api.module.blog.models.CommentBlog;
import com.api.module.blog.repositories.BlogRepository;
import com.api.module.blog.repositories.CommentBlogRepository;
import com.api.module.blog.responses.CustomResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentBlogService implements CustomIActions<CommentBlogDto> {
    private final CommentBlogRepository commentBlogRepository;
    private final BlogRepository blogRepository;

    @Override
    public CustomResponse listAll() {
        return null;
    }

    @Override
    public CustomResponse getById(UUID id) {
        return null;
    }

    @Override
    public CustomResponse create(CommentBlogDto commentBlogDto) {
        final var blog = blogRepository.findById(commentBlogDto.getBlog().getBlogId())
                .orElseThrow(() -> new EntityNotFoundException("Blog not found."));

        final var obj = CommentBlog.builder()
                .blog(blog)
                .user(commentBlogDto.getUser())
                .publishedDate(LocalDate.now())
                .content(commentBlogDto.getContent())
                .build();

        final var saved = commentBlogRepository.save(obj);

        return CustomResponse.builder().data(saved).build();
    }

    @Override
    public CustomResponse update(UUID id, CommentBlogDto commentBlogDto) {
        return null;
    }

    @Override
    public CustomResponse delete(UUID id) {
        return null;
    }
}
