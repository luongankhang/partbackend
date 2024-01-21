package com.api.module.infoweb.controllers;

import com.api.module.infoweb.dtos.FaqDto;
import com.api.module.infoweb.repositories.FaqRepository;
import com.api.module.infoweb.responses.CustomResponse;
import com.api.module.infoweb.services.FaqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/msshop/mif/faq")
@RequiredArgsConstructor
public class FaqController {
    private final FaqService faqService;
    private final FaqRepository faqRepository;

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(faqRepository.findAll());
    }

    @PostMapping
    private ResponseEntity<CustomResponse> create(@Valid @RequestBody FaqDto faqDto) {
        return ResponseEntity.ok(faqService.create(faqDto));
    }
}
