package com.centric.productapiservice.common.exception;

public class InvalidRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 5011748188535651893L;

	public InvalidRequestException(String message) { 
		super(message);
	}
}
