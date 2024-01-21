package com.api.module.gateway.tokens;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TokenFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String token = extractTokenFromOriginalRequest(exchange.getRequest());
		ServerHttpRequest modifiedRequest = exchange.getRequest().mutate().header("Authorization", "Bearer " + token)
				.build();
		ServerWebExchange modifiedExchange = exchange.mutate().request(modifiedRequest).build();

		return chain.filter(modifiedExchange);
	}

	private String extractTokenFromOriginalRequest(ServerHttpRequest request) {
		return null;
	}
}
