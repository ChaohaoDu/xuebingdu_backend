package com.xuebingdu.frontend.service;

import com.xuebingdu.frontend.dao.OrderRepository;
import com.xuebingdu.frontend.entity.Order;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public String updateState(Order order) {
		System.out.println("reachieved");
		Order orderToUpdate = orderRepository.getById(order.getId());
		orderToUpdate.setStatus(order.getStatus());
		orderToUpdate.setLastUpdated(new Date());
		orderRepository.save(orderToUpdate);
		return "success";
	}
}
