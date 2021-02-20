package com.xyz.order.model.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericAddress {

	private String phoneNumber;
	private String contactName;
	private String place;
	private String city;
	private String pincode;
	private String state;
	private String country;

	

}
