package controller;

import entities.Order;
import facade.CheckoutFacade;
import payment.adapter.PaymentGateway;
import payment.factory.PaymentFactory;
import payment.product.Payment;

public class CheckoutController {

	private final CheckoutFacade checkoutFacade;

	public CheckoutController(CheckoutFacade checkoutFacade) {
		this.checkoutFacade = checkoutFacade;
	}

	public Payment checkout(Order order, PaymentFactory paymentFactory, PaymentGateway paymentGateway,
			String paymentCode, String currency) {

		return checkoutFacade.checkout(order, paymentFactory, paymentGateway, paymentCode, currency);
	}
}