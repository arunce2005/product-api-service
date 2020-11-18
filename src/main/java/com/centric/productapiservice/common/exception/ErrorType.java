package com.centric.productapiservice.common.exception;

public enum ErrorType {
	
	INVALID_REQUEST(1, "Your request is invalid "),
	NOT_FOUND(2, "Requested object not found");
	
	private int code;
	private String message;
	
	ErrorType(int code, String message) {
		this.code = code;
		this.message = message;
	}
	

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
