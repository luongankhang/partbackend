package com.api.module.gateway.configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes().route("auth_service_route",
				r -> r.path("/api/mshop/mc/auth/**").and().method("POST").uri("http://localhost:8080")).build();
	}
}
