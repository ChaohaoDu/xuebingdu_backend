package com.xuebingdu.frontend.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.xuebingdu.frontend.dto.PaymentInfo;
import com.xuebingdu.frontend.dto.Purchase;
import com.xuebingdu.frontend.dto.PurchaseResponse;

public interface CheckoutService {

	PurchaseResponse placeOrder(Purchase purchase);

	PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;

}
