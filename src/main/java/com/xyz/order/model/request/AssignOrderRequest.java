package com.xyz.order.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * This class is used to store the user login request
 */
@NoArgsConstructor
@Getter
@Setter
public class AssignOrderRequest {

	@ApiModelProperty(value = "Order id", required = true)
	private String orderId;

	@ApiModelProperty(value = "User id of the delivery agent", required = true)
	private String userId;

	@ApiModelProperty(value = "PickUp Point Address", required = true)
	private String trackingStatus;

	@ApiModelProperty(value = "Tracking id or tracking URL", required = true)
	private String trackingId;

	@ApiModelProperty(value = "Date of delivery", required = true)
	private String deliveryDate;

	@ApiModelProperty(value = "ShippingAddress", required = false)
	private String userAvailability;

}
