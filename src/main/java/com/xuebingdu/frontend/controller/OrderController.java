package com.xuebingdu.frontend.controller;

import com.xuebingdu.frontend.dao.AddressRepository;
import com.xuebingdu.frontend.dao.CustomerRepository;
import com.xuebingdu.frontend.dao.OrderRepository;
import com.xuebingdu.frontend.entity.Address;
import com.xuebingdu.frontend.entity.Order;
import com.xuebingdu.frontend.entity.OrderItem;
import com.xuebingdu.frontend.service.MailService;
import com.xuebingdu.frontend.service.MailTemplate;
import com.xuebingdu.frontend.service.OrderService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.transaction.Transactional;
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
	private final OrderRepository orderRepository;
	private final MailService mailService;
	private final AddressRepository addressRepository;
	private final CustomerRepository customerRepository;

	public OrderController(OrderService orderService, OrderRepository orderRepository, MailService mailService,
	   AddressRepository addressRepository, CustomerRepository customerRepository) {
		this.orderService = orderService;
		this.orderRepository = orderRepository;
		this.mailService = mailService;
		this.addressRepository = addressRepository;
		this.customerRepository = customerRepository;
	}

	@PostMapping("/update-state")
	@Transactional
	public ResponseEntity<String> updateState(@RequestBody Order order) {
		Order orderToUpdate = orderRepository.getById(order.getId());
		orderToUpdate.setLastUpdated(new Date());
		orderToUpdate.setStatus("shipped");
		orderRepository.save(orderToUpdate);
		sendNotification(orderToUpdate);
		return new ResponseEntity<>("Complete", HttpStatus.OK);
	}

	private void sendNotification(Order order) {
		String content = generateContent(order);
		mailService.sendWithHtml(order.getCustomer().getEmail(), "Your order was shipped! - Xuebing Du", content);
	}

	private String generateContent(Order order) {
		StringBuilder sb = new StringBuilder();

		String part1 = MailTemplate.firstPart;
		String customerName = order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName();

		String pattern = "dd MMMM yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		sb.append(part1.replace("{{theme}}", "Your order was shipped").replace("{{content}}",
			  "Thank you for your order. Your order was shipped on " + simpleDateFormat.format(order.getLastUpdated()))
		   .replace("{{name}}", customerName).replace("{{tracking-number}}", order.getOrderTrackingNumber())
		   .replace("{{placed-date}}", simpleDateFormat.format(order.getDateCreated())));

		for (OrderItem orderItem : order.getOrderItems()) {
			String itemHtml = MailTemplate.itemTemplate;
			BigDecimal price = orderItem.getUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
			price.setScale(2, RoundingMode.HALF_EVEN);
			sb.append(itemHtml.replace("{{product-imageUrl}}", orderItem.getImageUrl())
			   .replace("{{product-name}}", orderItem.getName()).replace("{{product-size}}", orderItem.getSize())
			   .replace("{{product-quantity}}", String.valueOf(orderItem.getQuantity()))
			   .replace("{{product-price}}", price.toString()));
		}

		String part2 = MailTemplate.lastPart;
		Address address = order.getAddress();
		String shippingMethod;

		switch (order.getShipping().setScale(2, RoundingMode.HALF_EVEN).toString()) {
			case "30.00":
				shippingMethod = "First-Class International";
				break;
			case "60.00":
				shippingMethod = "Priority Mail International";
				break;
			case "10.00":
			default:
				shippingMethod = "US Shipping";
		}

		BigDecimal productTotalPrice = order.getProductTotalPrice();
		BigDecimal shipping = order.getShipping();

		sb.append(part2.replace("{{subtotal}}", productTotalPrice.setScale(2).toString())
		   .replace("{{shipping}}", shipping.setScale(2).toString())
		   .replace("{{total-price}}", productTotalPrice.add(shipping).setScale(2).toString())
		   .replace("{{name}}", customerName).replace("{{street1}}", address.getStreet1())
		   .replace("{{street2}}", address.getStreet2().equals("") ? "" : address.getStreet2() + "</br>")
		   .replace("{{city}}", address.getCity()).replace("{{zip-code}}", address.getZipCode())
		   .replace("{{country}}", address.getCountry()).replace("{{email}}", order.getCustomer().getEmail())
		   .replace("{{shipping-method}}", shippingMethod));

		return sb.toString();
	}

}
