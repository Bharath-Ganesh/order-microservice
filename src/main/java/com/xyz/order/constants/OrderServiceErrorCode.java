package com.xyz.order.constants;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum OrderServiceErrorCode {
    
	ORDERID_INVALID("ORDER_101"),
	ORDER_NOT_ASSIGNED("ORDER_102");

	private String code;

	OrderServiceErrorCode(String errorCode) {
		this.code = errorCode;
	}

}
