package com.api.module.cart.controllers;

import com.api.module.cart.dtos.CartDto;
import com.api.module.cart.repositories.CartRepository;
import com.api.module.cart.responses.CustomResponse;
import com.api.module.cart.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/msshop/mct/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartRepository cartRepository;

    @GetMapping
    public ResponseEntity listALl() {
        return ResponseEntity.ok(cartRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomResponse> addCart(@RequestBody CartDto cartDto) {
        return ResponseEntity.ok(cartService.create(cartDto));
    }

    @DeleteMapping("/{user}/{product}")
    public ResponseEntity<CustomResponse> deleteCartByUserAndProduct(
            @PathVariable String user,
            @PathVariable String product
    ) {
        return ResponseEntity.ok(cartService.deleteCart(user, product));
    }

    @DeleteMapping("/{user}")
    public ResponseEntity<CustomResponse> deleteByUser(@PathVariable String user) {
        return ResponseEntity.ok(cartService.deleteAll(user));
    }
}
