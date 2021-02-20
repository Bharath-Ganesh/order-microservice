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
public class CreateOrderRequest {

	@ApiModelProperty(value = "ParcelDimension", required = true)
	private ParcelDimension parcelDimension;

	@ApiModelProperty(value = "PickUp Point Address", required = true)
	private GenericAddress pickUpPointAddress;

	@ApiModelProperty(value = "ShippingAddress", required = false)
	private GenericAddress shippingAddress;

	@ApiModelProperty(value = "Amount", required = false)
	private String amount;

}
