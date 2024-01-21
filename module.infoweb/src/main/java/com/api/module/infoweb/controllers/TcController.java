package com.api.module.infoweb.controllers;

import com.api.module.infoweb.dtos.TcDto;
import com.api.module.infoweb.repositories.TcRepository;
import com.api.module.infoweb.responses.CustomResponse;
import com.api.module.infoweb.services.TcService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/msshop/mif/tc")
@RequiredArgsConstructor
public class TcController {
    private final TcService tcService;
    private final TcRepository tcRepository;

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(tcRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody TcDto tcDto) {
        return ResponseEntity.ok(tcService.create(tcDto));
    }
}
