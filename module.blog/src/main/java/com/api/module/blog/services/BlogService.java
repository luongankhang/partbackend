package com.api.module.blog.services;

import com.api.module.blog.dtos.BlogDto;
import com.api.module.blog.models.Blog;
import com.api.module.blog.repositories.BlogRepository;
import com.api.module.blog.responses.CustomResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlogService implements CustomIActions<BlogDto> {
    private final BlogRepository blogRepository;

    @Override
    public CustomResponse listAll() {
        return null;
    }

    @Override
    public CustomResponse getById(UUID id) {
        try {
            final UUID uuid = UUID.fromString(String.valueOf(id));
        } catch (IllegalArgumentException e) {
            return CustomResponse.builder().message("Invalid UUID format for id.").build();
        }

        final var blog = blogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found."));

        return CustomResponse.builder().data(blog).build();
    }

    @Override
    public CustomResponse create(BlogDto blogDto) {
        var obj = Blog.builder()
                .title(blogDto.getTitle())
                .user(blogDto.getUser())
                .publishedDate(LocalDate.now())
                .content(blogDto.getContent())
                .fileName(blogDto.getFileName())
                .build();

        var saved = blogRepository.save(obj);

        return CustomResponse.builder().data(obj).build();
    }

    @Override
    public CustomResponse update(UUID id, BlogDto blogDto) {
        try {
            final UUID uuid = UUID.fromString(String.valueOf(id));
        } catch (IllegalArgumentException e) {
            return CustomResponse.builder().message("Invalid UUID format for id.").build();
        }

        final var blog = blogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found."));

        final var updated = Blog.builder()
                .title(blogDto.getTitle())
                .content(blogDto.getContent())
                .fileName(blogDto.getFileName())
                .build();
        blog.updateFrom(updated);

        final var saved = blogRepository.save(blog);

        return CustomResponse.builder().data(saved).build();
    }

    @Override
    public CustomResponse delete(UUID id) {
        try {
            final UUID uuid = UUID.fromString(String.valueOf(id));
        } catch (IllegalArgumentException e) {
            return CustomResponse.builder().message("Invalid UUID format for id.").build();
        }

        final var blog = blogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found."));

        blogRepository.delete(blog);

        return CustomResponse.builder().message("OK").build();
    }
}
