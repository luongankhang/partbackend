package com.api.module.infoweb.controllers;

import com.api.module.infoweb.dtos.BannerDto;
import com.api.module.infoweb.repositories.BannerRepository;
import com.api.module.infoweb.responses.CustomResponse;
import com.api.module.infoweb.services.BannerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/msshop/mif/banner")
@RequiredArgsConstructor
public class BannerController {
    private final BannerService bannerService;
    private final BannerRepository bannerRepository;

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(bannerRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody BannerDto bannerDto) {
        return ResponseEntity.ok(bannerService.create(bannerDto));
    }
}
