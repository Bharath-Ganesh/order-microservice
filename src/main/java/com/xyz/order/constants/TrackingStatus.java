package com.xyz.order.constants;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum TrackingStatus {

	SHIPPING("SHIPPING"), SHIPPED("SHIPPED"), DELIVERED("DELIVERED");

	private String status;

	TrackingStatus(String currentStatus) {
		this.status = currentStatus;
	}

}
