package com.api.module.customer.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

	public TokenResponse() {
		super();
	}

	public TokenResponse(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public static class TokenResponseBuilder {
		private String accessToken;
		private String refreshToken;

		public TokenResponseBuilder withAccessToken(String accessToken) {
			this.accessToken = accessToken;
			return this;
		}

		public TokenResponseBuilder withRefreshToken(String refreshToken) {
			this.refreshToken = refreshToken;
			return this;
		}

		public TokenResponse build() {
			return new TokenResponse(accessToken, refreshToken);
		}
	}
}
