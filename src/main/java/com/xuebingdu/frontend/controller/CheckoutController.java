package com.xuebingdu.frontend.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.xuebingdu.frontend.dto.PaymentInfo;
import com.xuebingdu.frontend.dto.Purchase;
import com.xuebingdu.frontend.dto.PurchaseResponse;
import com.xuebingdu.frontend.service.CheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

	private final CheckoutService checkoutService;

	public CheckoutController(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}

	@PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
		return checkoutService.placeOrder(purchase);
	}

	@PostMapping("/payment-intent")
	public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {
		PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);
		String paymentStr = paymentIntent.toJson();
		return new ResponseEntity<>(paymentStr, HttpStatus.OK);
	}
}







