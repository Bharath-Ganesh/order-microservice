package com.xyz.order.constants;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum OrderServiceErrorMessage {

	ORDERID_INVALID("Order does not exist"),
	ORDER_NOT_ASSIGNED("The order is not assigned");

	private String message;

	OrderServiceErrorMessage(String errorMessage) {
		this.message = errorMessage;
	}

}
