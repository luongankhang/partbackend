package com.api.module.infoweb.controllers;

import com.api.module.infoweb.dtos.AboutDto;
import com.api.module.infoweb.repositories.AboutRepository;
import com.api.module.infoweb.responses.CustomResponse;
import com.api.module.infoweb.services.AboutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/msshop/mif/about")
@RequiredArgsConstructor
public class AboutController {
    private final AboutService aboutService;
    private final AboutRepository aboutRepository;

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(aboutRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody AboutDto aboutDto) {
        return ResponseEntity.ok(aboutService.create(aboutDto));
    }
}
