package com.api.module.cart.controllers;

import com.api.module.cart.dtos.WishDto;
import com.api.module.cart.repositories.WishRepository;
import com.api.module.cart.responses.CustomResponse;
import com.api.module.cart.services.WishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/msshop/mct/wish")
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;
    private final WishRepository wishRepository;

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(wishRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody WishDto wishDto) {
        return ResponseEntity.ok(wishService.create(wishDto));
    }

    @DeleteMapping("/{user}/{product}")
    public ResponseEntity<CustomResponse> deleteWishByUserAndProduct(
            @PathVariable String user,
            @PathVariable String product
    ) {
        return ResponseEntity.ok(wishService.deleteWish(user, product));
    }

    @DeleteMapping("/{user}")
    public ResponseEntity<CustomResponse> deleteByUser(@PathVariable String user) {
        return ResponseEntity.ok(wishService.deleteAll(user));
    }
}
