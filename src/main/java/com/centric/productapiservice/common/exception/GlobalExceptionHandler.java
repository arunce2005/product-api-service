package com.centric.productapiservice.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ErrorResponse> handleInvalidRequestException(HttpServletRequest req,
			InvalidRequestException ex) {
		return new ResponseEntity<>(
				new ErrorResponse(req, new ErrorMessage(ErrorType.INVALID_REQUEST, ex.getMessage())),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProductNotFoundException(HttpServletRequest req,
			ProductNotFoundException ex) {
		return new ResponseEntity<>(
				new ErrorResponse(req, new ErrorMessage(ErrorType.NOT_FOUND, ex.getMessage())),
				HttpStatus.NOT_FOUND);
	}
}
