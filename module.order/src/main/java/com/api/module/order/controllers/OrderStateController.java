package com.api.module.order.controllers;

import com.api.module.order.repositories.OrderStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/msshop/mo/orderstate")
@RequiredArgsConstructor
public class OrderStateController {
    private final OrderStateRepository orderStateRepository;

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(orderStateRepository.findAll());
    }
}
