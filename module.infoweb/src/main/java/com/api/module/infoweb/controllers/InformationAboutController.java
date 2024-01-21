package com.api.module.infoweb.controllers;

import com.api.module.infoweb.dtos.InformationAboutDto;
import com.api.module.infoweb.repositories.InformationAboutRepository;
import com.api.module.infoweb.services.InformationAboutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/msshop/mif/ib")
@RequiredArgsConstructor
public class InformationAboutController {
    private final InformationAboutService informationAboutService;
    private final InformationAboutRepository informationAboutRepository;

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(informationAboutRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody InformationAboutDto informationAboutDto) {
        return ResponseEntity.ok(informationAboutService.create(informationAboutDto));
    }
}
