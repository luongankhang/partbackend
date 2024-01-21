package com.api.module.gateway.controllers;

import com.api.module.gateway.services.AuthenticationServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
//    private final AuthenticationServiceFeignClient authenticationClient;
//
//    @Autowired
//    public GatewayController(AuthenticationServiceFeignClient authenticationClient) {
//        this.authenticationClient = authenticationClient;
//    }
//
//    @RequestMapping("/api/gateway/authenticate")
//    public String authenticate() {
//        return authenticationClient.authenticate();
//    }
}
