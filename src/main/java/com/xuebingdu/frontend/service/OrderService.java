package com.xuebingdu.frontend.service;

import com.xuebingdu.frontend.dto.UpdateResponse;
import com.xuebingdu.frontend.entity.Order;

public interface OrderService {
	String updateState(Order order);
}
