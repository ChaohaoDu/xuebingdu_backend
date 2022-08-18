package com.xuebingdu.frontend.controller;

import com.xuebingdu.frontend.entity.Order;
import com.xuebingdu.frontend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-service")
public class OrderController {
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/update-state")
	public ResponseEntity<String> updateState(@RequestBody Order order) {
		orderService.updateState(order);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
