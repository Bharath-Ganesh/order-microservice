package com.xyz.order.model.request.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.xyz.order.model.request.AssignOrderRequest;
import com.xyz.order.model.request.GenericAddress;

class GenericAddressTest {

	@Test
	void testAssignOrder() {
		GenericAddress genericAddress = new GenericAddress();
		genericAddress.setCity("state");
		genericAddress.setContactName("state");
		genericAddress.setCountry("state");
		genericAddress.setPhoneNumber("state");
		genericAddress.setPincode("state");
		genericAddress.setPlace("state");
		genericAddress.setState("state");
		
		assertEquals(genericAddress.getCity(), "state");
		assertEquals(genericAddress.getContactName(), "state");
		assertEquals(genericAddress.getCountry(), "state");
		assertEquals(genericAddress.getPhoneNumber(), "state");
		assertEquals(genericAddress.getPincode(), "state");
		assertEquals(genericAddress.getPlace(), "state");
		assertEquals(genericAddress.getState(), "state");

	}
}
