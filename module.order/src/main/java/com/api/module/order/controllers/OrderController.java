package com.api.module.order.controllers;

import com.api.module.order.dtos.OrderDto;
import com.api.module.order.repositories.OrderRepository;
import com.api.module.order.responses.CustomResponse;
import com.api.module.order.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/msshop/mo/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.create(orderDto));
    }
}
