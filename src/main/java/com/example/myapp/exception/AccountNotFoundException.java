package com.example.myapp.exception;

import lombok.Getter;

@Getter
public class AccountNotFoundException extends RuntimeException {
	
	private String email;

	public AccountNotFoundException(String email) {
		this.email = email;
	}
}
