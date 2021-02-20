package com.xyz.order.exception.handler;

import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.xyz.order.exception.OrderServiceException;
import com.xyz.order.model.error.Error;

@ControllerAdvice
public class OrderServiceExceptionHandler {

	@ExceptionHandler(value = OrderServiceException.class)
	public ResponseEntity<Object> exception(OrderServiceException userServiceException) {
		Error error = new Error();
		error.setErrorCode(userServiceException.getErrorCode());
		error.setErrorMessage(userServiceException.getErrorMessage());
		List<Error> errorList = new ArrayList<>();
		errorList.add(error);
		return new ResponseEntity<>(error, userServiceException.getHttpStatusCode());
	}

}
