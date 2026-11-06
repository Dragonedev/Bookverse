package controller;

import entities.Order;
import facade.CheckoutFacade;
import payment.factory.PaymentFactory;
import payment.product.Payment;

public class CheckoutController {

	private final CheckoutFacade checkoutFacade;

	public CheckoutController(CheckoutFacade checkoutFacade) {
		this.checkoutFacade = checkoutFacade;
	}

	public Payment checkout(Order order, PaymentFactory paymentFactory, String paymentCode, String currency) {

		return checkoutFacade.checkout(order, paymentFactory, paymentCode, currency);
	}
}