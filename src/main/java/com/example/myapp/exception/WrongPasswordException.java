package com.example.myapp.exception;

public class WrongPasswordException extends RuntimeException{

	public WrongPasswordException(String email) {
		super("Email: "+ email +" ----- Password is wrong");
	}
}
