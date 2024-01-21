package com.api.module.customer.controllers;

import com.api.module.customer.dtos.LoginRequest;
import com.api.module.customer.dtos.PasswordDto;
import com.api.module.customer.dtos.RegisterRequest;
import com.api.module.customer.dtos.UpdateDto;
import com.api.module.customer.repositories.AuthRepository;
import com.api.module.customer.responses.CustomResponse;
import com.api.module.customer.responses.TokenResponse;
import com.api.module.customer.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/mshop/mc/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthRepository authRepository;


    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(authRepository.findAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity finByUsername(@PathVariable String username) {
        var user = authRepository.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(authService.register(request));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TokenResponse("Xảy ra lỗi.", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new TokenResponse("Xảy ra lỗi.", e.getMessage()));
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<CustomResponse> update(@PathVariable String username, @RequestBody UpdateDto request) {
        return ResponseEntity.ok(authService.update(username, request));
    }

    @PutMapping("/password/{username}")
    public ResponseEntity<CustomResponse> updatePassword(@PathVariable String username, @RequestBody PasswordDto request) {
        try {
            return ResponseEntity.ok(authService.updatePassword(username, request));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(e.getMessage(), ""));
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(authService.authenticate(request));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new TokenResponse("Xảy ra lỗi.", "Sai tài khoản hoặc mật khẩu."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new TokenResponse("Xảy ra lỗi.", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<CustomResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
