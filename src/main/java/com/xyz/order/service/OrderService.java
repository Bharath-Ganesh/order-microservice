package com.xyz.order.service;

import java.util.List;

import com.xyz.order.entity.Orders;
import com.xyz.order.exception.OrderServiceException;
import com.xyz.order.model.request.AssignOrderRequest;
import com.xyz.order.model.request.CreateOrderRequest;
import com.xyz.order.model.response.CreateOrderResponse;

public interface OrderService {

	CreateOrderResponse createParcelRequest(CreateOrderRequest createParcelRequest);

	CreateOrderResponse assignOrder(AssignOrderRequest assignOrderRequest);

	List<Orders> fetchOrders(String status);

	Orders getOrderDetails(String orderId) throws OrderServiceException;

	List<Orders> getAssignedOrderDetails(String userId, String trackingStatus) throws OrderServiceException;

}
