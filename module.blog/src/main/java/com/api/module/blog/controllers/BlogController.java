package com.api.module.blog.controllers;

import com.api.module.blog.dtos.BlogDto;
import com.api.module.blog.repositories.BlogRepository;
import com.api.module.blog.responses.CustomResponse;
import com.api.module.blog.services.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/msshop/mb/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    private final BlogRepository blogRepository;

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(blogRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(blogService.getById(id));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody BlogDto blogDto) {
        return ResponseEntity.ok(blogService.create(blogDto));
    }
}
