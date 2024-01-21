package com.api.module.customer.services;

import com.api.module.customer.dtos.LoginRequest;
import com.api.module.customer.dtos.PasswordDto;
import com.api.module.customer.dtos.RegisterRequest;
import com.api.module.customer.dtos.UpdateDto;
import com.api.module.customer.models.Customer;
import com.api.module.customer.models.Role;
import com.api.module.customer.models.Token;
import com.api.module.customer.models.TokenType;
import com.api.module.customer.repositories.AuthRepository;
import com.api.module.customer.repositories.TokenRepository;
import com.api.module.customer.responses.CustomResponse;
import com.api.module.customer.responses.TokenResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
                       TokenRepository tokenRepository, AuthenticationManager authenticationManager) {
        super();
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public TokenResponse register(RegisterRequest request) {
        if (authRepository.existsByUsername(request.getUsername())) {
            throw new DataIntegrityViolationException("Tài khoản đã tồn tại.");
        }
//		if (authRepository.existsByPhone(request.getPhone())) {
//			throw new DataIntegrityViolationException("Số điện thoại đã tồn tại.");
//		}
//        if (authRepository.existsByEmail(request.getEmail())) {
//            throw new DataIntegrityViolationException("Email đã tồn tại.");
//        }
        var user = new Customer.CustomerBuilder().withUsername(request.getUsername())
                    .withPassword(passwordEncoder.encode(request.getPassword())).withRole(Role.USER).withFileName("123").build();
        var savedUser = authRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return new TokenResponse.TokenResponseBuilder().withAccessToken(jwtToken).withRefreshToken(refreshToken)
                .build();
    }

    public CustomResponse update(String username, UpdateDto updateDto) {
        var user = authRepository.findByUsername(username).orElseThrow();

        user.setFirstName(updateDto.getFirstName());
        user.setLastName(updateDto.getLastName());
        user.setAddress(updateDto.getAddress());
        user.setPhone(updateDto.getPhone());
        user.setEmail(updateDto.getEmail());

        var saved = authRepository.save(user);

        return CustomResponse.builder().data(saved).build();
    }

    public CustomResponse updatePassword(String username, PasswordDto passwordDto) {
        var user = authRepository.findByUsername(username).orElseThrow();
        if (!passwordEncoder.matches(passwordDto.getPasswordFirst(), user.getPassword())) {
            throw new DataIntegrityViolationException("Mật khẩu không đúng.");
        }

        user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));

        var saved = authRepository.save(user);

        return CustomResponse.builder().data(saved).build();
    }

    public TokenResponse authenticate(LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = authRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new TokenResponse.TokenResponseBuilder().withAccessToken(jwtToken).withRefreshToken(refreshToken)
                .build();
    }

    public CustomResponse login(LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = authRepository.findByUsername(request.getUsername()).orElseThrow();
        return CustomResponse.builder().data(user.getRole()).build();
    }

    private void saveUserToken(Customer user, String jwtToken) {
        var token = new Token.TokenBuilder().withToken(jwtToken).withTokenType(TokenType.BEARER).withExpired(false)
                .withRevoked(false).withCustomer(user).build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Customer user) {
        var validUserTokens = tokenRepository.findAllValidTokenByCustomer(user.getCustomerId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
