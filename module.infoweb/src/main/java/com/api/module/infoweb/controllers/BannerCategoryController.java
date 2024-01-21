package com.api.module.infoweb.controllers;

import com.api.module.infoweb.dtos.BannerCategoryDto;
import com.api.module.infoweb.repositories.BannerCategoryRepository;
import com.api.module.infoweb.responses.CustomResponse;
import com.api.module.infoweb.services.BannerCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/msshop/mif/bc")
@RequiredArgsConstructor
public class BannerCategoryController {
    private final BannerCategoryService bannerCategoryService;
    private final BannerCategoryRepository bannerCategoryRepository;

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(bannerCategoryRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody BannerCategoryDto bannerCategoryDto) {
        return ResponseEntity.ok(bannerCategoryService.create(bannerCategoryDto));
    }
}
