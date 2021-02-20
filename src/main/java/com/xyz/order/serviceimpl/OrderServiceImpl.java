package com.xyz.order.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xyz.order.constants.OrderServiceErrorCode;
import com.xyz.order.constants.OrderServiceErrorMessage;
import com.xyz.order.constants.TrackingStatus;
import com.xyz.order.entity.Orders;
import com.xyz.order.exception.OrderServiceException;
import com.xyz.order.model.request.AssignOrderRequest;
import com.xyz.order.model.request.CreateOrderRequest;
import com.xyz.order.model.response.CreateOrderResponse;
import com.xyz.order.model.response.UserResponse;
import com.xyz.order.repository.OrderRepository;
import com.xyz.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${user.status.updateURL}")
	private String statusupdateURL;

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Override
	public CreateOrderResponse createParcelRequest(CreateOrderRequest createOrderRequest) {
		// TODO Auto-generated method stub

		CreateOrderResponse createOrderResponse = new CreateOrderResponse();
		if (null != createOrderRequest) {
			Orders orders = convertIntoOrderEntity(createOrderRequest);
			if (orders != null) {
				try {
					orderRepository.save(orders);
					createOrderResponse.setMessage("Parcel added successfully");
				} catch (Exception ex) {
					// TODO: handle exception
					logger.info("Caught Exception while saving into database", ex);
					createOrderResponse.setMessage("Something went wrong");
				}
			}

		}
		return createOrderResponse;
	}

	public Orders convertIntoOrderEntity(CreateOrderRequest createOrderRequest) {
		Orders orders = new Orders();

		if (null != createOrderRequest && null != createOrderRequest.getParcelDimension()) {
			orders.setHeight(createOrderRequest.getParcelDimension().getHeight());
			orders.setLength(createOrderRequest.getParcelDimension().getLength());
			orders.setWidth(createOrderRequest.getParcelDimension().getBreadth());
			orders.setWeight(createOrderRequest.getParcelDimension().getWeight());
			orders.setParcelType(createOrderRequest.getParcelDimension().getTypeOfParcel());
		}

		if (null != createOrderRequest.getPickUpPointAddress() && null != createOrderRequest.getShippingAddress()) {

			// The city is taken as the address of the source and destination .
			orders.setPickUpPoint(createOrderRequest.getPickUpPointAddress().getCity());
			orders.setShippingAddress(createOrderRequest.getShippingAddress().getCity());
		}

		// Create a date object
		LocalDate orderDate = LocalDate.now();
		orders.setOrderDate(orderDate.toString());
		orders.setTrackingStatus("S");

		/**
		 * The tracking id should be generated from the shipment label created. Shipment
		 * label creation can be a future scope.
		 */

		try {
			orders.setAmount(Double.parseDouble(createOrderRequest.getAmount()));

		} catch (Exception ex) {
			logger.info("Caught Exception while converting amount to ", ex);
		}
		;
		orders.setTrackingStatus(TrackingStatus.SHIPPING.name());

		return orders;

	}

	/*
	 * An alternate strcuture could be
	 */

// Code refactoring has to be done. The code contains multiple if conditions
	@Override
	public CreateOrderResponse assignOrder(AssignOrderRequest assignOrderRequest) {
		// TODO Auto-generated method stub

		CreateOrderResponse createOrderResponse = null;
		String message = "";
		if (null != assignOrderRequest && !assignOrderRequest.getOrderId().isEmpty()
				&& !assignOrderRequest.getUserId().isEmpty()) {

			Integer orderId = Integer.parseInt(assignOrderRequest.getOrderId());
			Optional<Orders> orderEntity = orderRepository.findById(orderId);

			/**
			 * The order assigning operation should only be completed if the two database
			 * operation are successful. A Logic to validate the two operation
			 */

			if (orderEntity.isPresent()) {
				createOrderResponse = new CreateOrderResponse();
				/*
				 * The username is from ui against an order . The validation for checking user
				 * can be within the scope for development
				 */
				try {
					Orders order = orderEntity.get();

					/**
					 * The below fields are updated against the null check condition. If null values
					 * won't be updated
					 */
					if(assignOrderRequest.getTrackingStatus().equals("SHIPPED")) {
						LocalDate deliveryDate = LocalDate.now();
						order.setDeliveredDate(deliveryDate.toString());
					}else {
						if(!assignOrderRequest.getDeliveryDate().isEmpty()) {
							order.setDeliveredDate(assignOrderRequest.getDeliveryDate());
						}
					}

					if (!assignOrderRequest.getTrackingId().isEmpty()) {
						order.setTrackingId(assignOrderRequest.getTrackingId());
					}

					UserResponse userResponse = new UserResponse();
					if (!assignOrderRequest.getUserId().isEmpty()
							&& !assignOrderRequest.getUserAvailability().isEmpty()) {
						userResponse = callingUserMicroservice(assignOrderRequest.getUserId(),
								assignOrderRequest.getUserAvailability());
					}
					// Rest api call to user microservice to update the user availability status

					if (null != userResponse) {
						Integer id = Integer.parseInt(assignOrderRequest.getUserId());
						order.setAgentId(id);
						if (assignOrderRequest.getTrackingStatus().isEmpty()) {
							order.setTrackingStatus(TrackingStatus.SHIPPING.name());
						} else {
							/*
							 * Switch to check whether the tracking status is present
							 */

							order.setTrackingStatus(assignOrderRequest.getTrackingStatus());
						}

						orderRepository.save(order);
						message = "Order created successfully";

					}
				} catch (Exception ex) {
					logger.info("Caught Exception while saving into database", ex);
				}

			} else {
				// Custom exception
				message = "Something went wrong. Order does not exist";
			}

		}
		createOrderResponse.setMessage(message);
		return createOrderResponse;
	}

	private UserResponse callingUserMicroservice(String username, String userAvailability) {
		// TODO Auto-generated method stub

		String url = statusupdateURL + username + "/" + userAvailability;
		return restTemplate.getForObject(url, UserResponse.class);

	}

	@Override
	public List<Orders> fetchOrders(String status) {
		// TODO Auto-generated method stub

		List<Orders> orders = new ArrayList<>();
		if (!status.isEmpty()) {
			// Map the entity object has to be converted into a response object
			Optional<List<Orders>> ordersList = orderRepository.findByTrackingStatus(status.toUpperCase());
			if (ordersList.isPresent()) {
				orders = ordersList.get();
			}

		}
		return orders;

	}

	@Override
	public Orders getOrderDetails(String orderId) throws OrderServiceException {
		// TODO Auto-generated method stub
		Orders orders = null;
		if (!orderId.isEmpty()) {
			// Map the entity object has to be converted into a response object
			Integer id = Integer.parseInt(orderId);
			Optional<Orders> orderEntity = orderRepository.findById(id);
			if (orderEntity.isPresent()) {
				orders = orderEntity.get();
			}

		} else {
			throw new OrderServiceException(OrderServiceErrorCode.ORDERID_INVALID.getCode(),
					OrderServiceErrorMessage.ORDERID_INVALID.getMessage(), HttpStatus.BAD_REQUEST, null);
		}
		return orders;

	}

	@Override
	public List<Orders> getAssignedOrderDetails(String userId, String trackingStatus) throws OrderServiceException {
		// TODO Auto-generated method stub
		List<Orders> orders = null;
		if (!userId.isEmpty() && !trackingStatus.isEmpty()) {
			// Map the entity object has to be converted into a response object
			Integer id = Integer.parseInt(userId);

			Optional<List<Orders>> orderEntityList = orderRepository.findByAgentIdAndTrackingStatus(id, trackingStatus);
			if (orderEntityList.isPresent()) {
				orders = orderEntityList.get();
			} else {
				throw new OrderServiceException(OrderServiceErrorCode.ORDER_NOT_ASSIGNED.getCode(),
						OrderServiceErrorMessage.ORDER_NOT_ASSIGNED.getMessage(), HttpStatus.BAD_REQUEST, null);
			}

		} else {
			throw new OrderServiceException(OrderServiceErrorCode.ORDER_NOT_ASSIGNED.getCode(),
					OrderServiceErrorMessage.ORDER_NOT_ASSIGNED.getMessage(), HttpStatus.BAD_REQUEST, null);
		}
		return orders;
	}

//	public <T,S> T makePostRequest(S requestBody,String url,String method,HttpHeaders header,
//			Class<T> responseClass){
//		T responseEntity=null;
//		HttpEntity<S> entity=new HttpEntity<>(requestBody,header);
//		try {
//			responseEntity=restTemplate.exchange(url, HttpMethod.POST, entity, responseClass).getBody();
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//		return responseEntity;
//	}
}
