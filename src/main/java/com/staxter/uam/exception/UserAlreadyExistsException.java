package com.staxter.uam.exception;

public class UserAlreadyExistsException extends RuntimeException{

	public UserAlreadyExistsException(String exceptionMessage) {
			super(exceptionMessage);
	}
}
