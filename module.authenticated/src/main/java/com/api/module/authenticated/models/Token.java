package com.api.module.authenticated.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblToken")
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID tokenId;

	@Column(unique = true)
	private String token;

	@Enumerated(EnumType.STRING)
	private TokenType tokenType = TokenType.BEARER;

	@SuppressWarnings("unused")
	private boolean revoked;

	public boolean expired;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId")
	public Customer customer;

	public Token() {
		super();
	}

	public Token(String token, TokenType tokenType, boolean revoked, boolean expired, Customer customer) {
		super();
		this.token = token;
		this.tokenType = tokenType;
		this.revoked = revoked;
		this.expired = expired;
		this.customer = customer;
	}

	public UUID getTokenId() {
		return tokenId;
	}

	public void setTokenId(UUID tokenId) {
		this.tokenId = tokenId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}