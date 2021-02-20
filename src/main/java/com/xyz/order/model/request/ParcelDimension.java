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
public class ParcelDimension {

	@ApiModelProperty(value = "Height of the parcel", required = true)
	private Double height;

	@ApiModelProperty(value = "Breadth of the parcel", required = true)
	private Double breadth;

	@ApiModelProperty(value = "Length of the parcel", required = true)
	private Double length;

	@ApiModelProperty(value = "Weight of the parcel", required = true)
	private Double weight;

	@ApiModelProperty(value = "Type of the parcel", required = true)
	private String typeOfParcel;

	/**
	 * @return the height
	 */
	public Double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Double height) {
		this.height = height;
	}

	/**
	 * @return the breadth
	 */
	public Double getBreadth() {
		return breadth;
	}

	/**
	 * @param breadth the breadth to set
	 */
	public void setBreadth(Double breadth) {
		this.breadth = breadth;
	}

	/**
	 * @return the length
	 */
	public Double getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(Double length) {
		this.length = length;
	}

	/**
	 * @return the typeOfParcel
	 */
	public String getTypeOfParcel() {
		return typeOfParcel;
	}

	/**
	 * @param typeOfParcel the typeOfParcel to set
	 */
	public void setTypeOfParcel(String typeOfParcel) {
		this.typeOfParcel = typeOfParcel;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

}
