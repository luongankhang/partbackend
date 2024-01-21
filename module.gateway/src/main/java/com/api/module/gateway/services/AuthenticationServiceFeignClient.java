package com.api.module.gateway.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "authentication-service")
public interface AuthenticationServiceFeignClient {

    @PostMapping("/api/authenticate")
    String authenticate();
}
