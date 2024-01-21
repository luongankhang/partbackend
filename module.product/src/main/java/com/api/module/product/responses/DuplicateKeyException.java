package com.api.module.product.responses;

@SuppressWarnings("serial")
public class DuplicateKeyException extends RuntimeException {
	public DuplicateKeyException(String message) {
		super(message);
	}
}
