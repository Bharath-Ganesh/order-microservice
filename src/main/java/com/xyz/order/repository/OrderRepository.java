package com.xyz.order.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xyz.order.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {


	Optional<List<Orders>> findByTrackingStatus(String status);

	Optional<List<Orders>> findByAgentIdAndTrackingStatus(Integer id, String trackingStatus);

}
