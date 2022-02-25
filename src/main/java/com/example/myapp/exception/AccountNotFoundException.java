package com.example.myapp.exception;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(String email) {
		super(email + " is not existed");
	}
}
