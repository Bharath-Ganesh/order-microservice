package com.xyz.order.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899011392674690821L;
	private final String errorCode;
	private final String errorMessage;
	private final HttpStatus httpStatusCode;

	public OrderServiceException(String errorCode, String errorMessage, HttpStatus httpStatusCode, Throwable throwable) {

		super(errorMessage, throwable);
		this.httpStatusCode = httpStatusCode;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

}
