package com.xuebingdu.frontend.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.xuebingdu.frontend.UUIDUtil;
import com.xuebingdu.frontend.entity.Address;
import com.xuebingdu.frontend.entity.Customer;
import com.xuebingdu.frontend.entity.Order;
import com.xuebingdu.frontend.entity.OrderItem;
import com.xuebingdu.frontend.dao.CustomerRepository;
import com.xuebingdu.frontend.dto.PaymentInfo;
import com.xuebingdu.frontend.dto.Purchase;
import com.xuebingdu.frontend.dto.PurchaseResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	private final CustomerRepository customerRepository;
	private final MailService mailService;

	public CheckoutServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret}") String secretKey,
	   MailService mailService) {
		this.customerRepository = customerRepository;
		this.mailService = mailService;
		// initialize Stripe API with secret key
		Stripe.apiKey = secretKey;
	}

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		// retrieve the order info from dto
		Order order = purchase.getOrder();
		order.setStatus("prepare");
		// generate tracking number
		String orderTrackingNumber = UUIDUtil.getBase64();
		order.setOrderTrackingNumber(orderTrackingNumber);

		// populate order with orderItems
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));

		// populate order with billingAddress and shippingAddress
		order.setAddress(purchase.getAddress());

		// populate customer with order
		Customer customer = purchase.getCustomer();

		customer.add(order);

		Address address = purchase.getAddress();
		address.add(order);
		// save to the database
		customerRepository.save(customer);

		sendReceipt(purchase, orderTrackingNumber);
		// return a response
		return new PurchaseResponse(orderTrackingNumber);
	}



	@Override
	public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

		List<String> paymentMethodTypes = new ArrayList<>();
		paymentMethodTypes.add("card");

		Map<String, Object> params = new HashMap<>();
		params.put("amount", paymentInfo.getAmount());
		params.put("currency", paymentInfo.getCurrency());
		params.put("payment_method_types", paymentMethodTypes);
		params.put("description", "Luv2Shop purchase");
		params.put("receipt_email", paymentInfo.getReceiptEmail());

		return PaymentIntent.create(params);
	}


	private void sendReceipt(Purchase purchase, String orderTrackingNumber) {
		String content = generateContent(purchase, orderTrackingNumber);
		mailService.sendWithHtml(purchase.getCustomer().getEmail(), "Thank you for your purchase - Xuebing Du", content);
		mailService.sendToMyselfWithHtml("You have a new order - Xuebing Du", content);
	}

	private String generateContent(Purchase purchase, String orderTrackingNumber) {
		StringBuilder sb = new StringBuilder();

		String part1 = MailTemplate.firstPart;
		String customerName = purchase.getCustomer().getFirstName() + " " + purchase.getCustomer().getLastName();

		String pattern = "dd MMMM yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		sb.append(part1.replace("{{name}}", customerName).replace("{{tracking-number}}", orderTrackingNumber)
		   .replace("{{placed-date}}", simpleDateFormat.format(new Date())));

		for (OrderItem orderItem : purchase.getOrderItems()) {
			String itemHtml = MailTemplate.itemTemplate;
			BigDecimal price = orderItem.getUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
			price.setScale(2, RoundingMode.HALF_EVEN);
			sb.append(itemHtml.replace("{{product-imageUrl}}", orderItem.getImageUrl())
			   .replace("{{product-name}}", orderItem.getName()).replace("{{product-size}}", orderItem.getSize())
			   .replace("{{product-quantity}}", String.valueOf(orderItem.getQuantity()))
			   .replace("{{product-price}}", price.toString()));
		}

		String part2 = MailTemplate.lastPart;
		Address address = purchase.getAddress();
		String shippingMethod;

		switch (purchase.getOrder().getShipping().setScale(2, RoundingMode.HALF_EVEN).toString()) {
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

		BigDecimal productTotalPrice = purchase.getOrder().getProductTotalPrice();
		BigDecimal shipping  = purchase.getOrder().getShipping();

		sb.append(part2
			  .replace("{{subtotal}}",productTotalPrice.setScale(2).toString())
			  .replace("{{shipping}}", shipping.setScale(2).toString())
			  .replace("{{total-price}}", productTotalPrice.add(shipping).setScale(2).toString())
		   .replace("{{name}}", customerName).replace("{{street1}}", address.getStreet1())
		   .replace("{{street2}}", address.getStreet2().equals("") ? "" : address.getStreet2() + "</br>")
		   .replace("{{city}}", address.getCity()).replace("{{zip-code}}", address.getZipCode())
		   .replace("{{country}}", address.getCountry()).replace("{{email}}", purchase.getCustomer().getEmail())
		   .replace("{{shipping-method}}", shippingMethod));

		return sb.toString();
	}
}

