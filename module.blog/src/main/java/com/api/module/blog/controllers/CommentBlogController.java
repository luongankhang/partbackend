package com.api.module.blog.controllers;

import com.api.module.blog.dtos.CommentBlogDto;
import com.api.module.blog.repositories.CommentBlogRepository;
import com.api.module.blog.responses.CustomResponse;
import com.api.module.blog.services.CommentBlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/msshop/mb/cmt")
@RequiredArgsConstructor
public class CommentBlogController {
    private final CommentBlogService commentBlogService;
    private final CommentBlogRepository commentBlogRepository;

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(commentBlogRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody CommentBlogDto commentBlogDto) {
        return ResponseEntity.ok(commentBlogService.create(commentBlogDto));
    }
}
