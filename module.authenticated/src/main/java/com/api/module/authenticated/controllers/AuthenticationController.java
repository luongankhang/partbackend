package com.api.module.authenticated.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/msshop/ma/authenticated")
public class AuthenticationController {

	@GetMapping
	public ResponseEntity authenticated() {
		return ResponseEntity.ok(null);
	}
}
