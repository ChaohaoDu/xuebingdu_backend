package com.xuebingdu.frontend.dto;

import com.xuebingdu.frontend.entity.Address;
import com.xuebingdu.frontend.entity.Customer;
import com.xuebingdu.frontend.entity.Order;
import com.xuebingdu.frontend.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

	private Customer customer;
	private Address shippingAddress;
	private Address billingAddress;
	private Order order;
	private Set<OrderItem> orderItems;

}
