package com.centric.productapiservice.common.exception;

public class ProductNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 8507989447847175556L;

	public ProductNotFoundException(String message) { 
		super(message);
	}
}
