package com.xyz.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.order.model.request.AssignOrderRequest;
import com.xyz.order.model.request.CreateOrderRequest;
import com.xyz.order.model.response.CreateOrderResponse;
import com.xyz.order.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.xyz.order.entity.Orders;
import com.xyz.order.exception.OrderServiceException;

@CrossOrigin
@Api(value = "Order Controller",tags = { "Order controller" })
@RestController
@RequestMapping("/order")
public class OrderServiceController {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceController.class);

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "To create a delivery entity", response = CreateOrderResponse.class)
	@PostMapping("/create")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Page not found"),
			@ApiResponse(code = 200, message = "CreateParcelResponse successful", response = CreateOrderResponse.class) })
	public ResponseEntity<CreateOrderResponse> createOrderRequest(@RequestBody CreateOrderRequest createOrderRequest) {
		logger.info("Entered createParcelRequest : ParcelController -> create method");
		CreateOrderResponse createParcelResponse = orderService.createParcelRequest(createOrderRequest);
		logger.info("Exited createParcelRequest ; ParcelController -> create method");

		return new ResponseEntity<>(createParcelResponse, HttpStatus.CREATED);

	}

	@ApiOperation(value = "To assign a delivery order to an agent and update the tracking status", response = CreateOrderResponse.class)
	@PostMapping("/assign")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Page not found"),
			@ApiResponse(code = 200, message = "CreateParcelResponse successful", response = CreateOrderResponse.class) })
	public ResponseEntity<CreateOrderResponse> assignOrder(@RequestBody AssignOrderRequest assignOrderRequest) {

		// The overall response time is the difference of two timestamps
		logger.info("Entered createParcelRequest : ParcelController, {} -> assignOrder", System.currentTimeMillis());
		CreateOrderResponse createParcelResponse = orderService.assignOrder(assignOrderRequest);
		logger.info("Exited createParcelRequest ; ParcelController {} -> assignOrder", System.currentTimeMillis());
		return new ResponseEntity<>(createParcelResponse, HttpStatus.CREATED);

	}

	@ApiOperation(value = "Fetches the order detail corresponding based on the tracking status", response = CreateOrderResponse.class)
	@GetMapping("/availability/{status}")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Page not found"),
			@ApiResponse(code = 200, message = "CreateParcelResponse successful", response = CreateOrderResponse.class) })
	public ResponseEntity<List<Orders>> fetchOrders(@PathVariable String status) {

		// The overall response time is the difference of two timestamps
		logger.info("Entered createParcelRequest : ParcelController, {}", System.currentTimeMillis());
		List<Orders> ordersList = orderService.fetchOrders(status);
		logger.info("Exited createParcelRequest ; ParcelController {}", System.currentTimeMillis());

		return new ResponseEntity<>(ordersList, HttpStatus.OK);

	}

	@ApiOperation(value = "Get order details for a particular order id", response = CreateOrderResponse.class)
	@GetMapping("/{orderId}")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Page not found"),
			@ApiResponse(code = 200, message = "CreateParcelResponse successful", response = Orders.class) })
	public ResponseEntity<Orders> getOrderDetails(@PathVariable("orderId") String orderId)
			throws OrderServiceException {

		// The overall response time is the difference of two timestamps
		logger.info("Entered createParcelRequest : ParcelController, {}", System.currentTimeMillis());
		Orders order = orderService.getOrderDetails(orderId);
		logger.info("Exited createParcelRequest ; ParcelController {}", System.currentTimeMillis());

		return new ResponseEntity<>(order, HttpStatus.OK);

	}

	@ApiOperation(value = "View the details of an order assigned to an agent based on the tracking status. By defaulg tracking status will be SHIPPED", response = CreateOrderResponse.class)
	@GetMapping("/trackingstatus/{userId}")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Page not found"),
			@ApiResponse(code = 200, message = "CreateParcelResponse successful", response = Orders.class) })
	public ResponseEntity<List<Orders>> getAssignedOrderDetails(@PathVariable("userId") String userId,
			@RequestParam(name = "trackingStatus", required = false, defaultValue = "SHIPPED") String trackingStatus)
			throws OrderServiceException {

		// The overall response time is the difference of two timestamps
		logger.info("Entered createParcelRequest : ParcelController, {}", System.currentTimeMillis());
		List<Orders> order = orderService.getAssignedOrderDetails(userId, trackingStatus);
		logger.info("Exited createParcelRequest ; ParcelController {}", System.currentTimeMillis());

		return new ResponseEntity<>(order, HttpStatus.OK);

	}

}
