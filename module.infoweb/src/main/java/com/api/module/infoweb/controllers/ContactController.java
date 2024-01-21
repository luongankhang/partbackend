package com.api.module.infoweb.controllers;

import com.api.module.infoweb.dtos.ContactDto;
import com.api.module.infoweb.repositories.ContactRepository;
import com.api.module.infoweb.responses.CustomResponse;
import com.api.module.infoweb.services.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/msshop/mif/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;
    private final ContactRepository contactRepository;

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(contactRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody ContactDto contactDto) {
        return ResponseEntity.ok(contactService.create(contactDto));
    }
}
