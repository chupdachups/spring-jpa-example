package com.example.myapp.exception;

public class LoginFailException extends RuntimeException {

	public LoginFailException(String email) {
		super(email + " is not existed");
	}
	
	public LoginFailException(String email, String password) {
		super("Password is wrong");
	}
}
