package com.xyz.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Orders", schema = "parcel_xyz")
@Getter
@Setter
public class Orders {

	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Integer orderId;

	private Double height;

	private Double length;

	private Double width;

	private Double weight;

	@Column(name = "parcel_type")
	private String parcelType;

	@Column(name = "pick_up_point")
	private String pickUpPoint;

	@Column(name = "shipping_address")
	private String shippingAddress;

	@Column(name = "order_date")
	private String orderDate;

	@Column(name = "delivered_date")
	private String deliveredDate;

	@Column(name = "delivery_agent_id")
	private Integer agentId;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "tracking_id")
	private String trackingId;

	@Column(name = "tracking_status")
	private String trackingStatus;

}
