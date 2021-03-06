package com.xyz.order.model.request.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.xyz.order.model.request.AssignOrderRequest;

public class AssignOrderRequestTest {

	@Test
	public void testAssignOrder() {
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
