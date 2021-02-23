package com.xyz.order.model.request.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.xyz.order.model.request.AssignOrderRequest;

class AssignOrderRequestTest {

	@Test
	void testAssignOrder() {
		AssignOrderRequest assignOrderRequest = new AssignOrderRequest();
		assignOrderRequest.setDeliveryDate("D");
		assignOrderRequest.setOrderId("D");
		assignOrderRequest.setTrackingId("D");
		assignOrderRequest.setTrackingStatus("D");
		assignOrderRequest.setUserAvailability("D");

		assertEquals(assignOrderRequest.getDeliveryDate(), "D");
		assertEquals(assignOrderRequest.getOrderId(), "D");
		assertEquals(assignOrderRequest.getTrackingId(), "D");
		assertEquals(assignOrderRequest.getTrackingStatus(), "D");
		assertEquals(assignOrderRequest.getUserAvailability(), "D");

	}

}
