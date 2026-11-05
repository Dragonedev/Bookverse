package controller;

import payment.product.Payment;
import service.PaymentService;

public class PaymentController {

	private final PaymentService service;

	public PaymentController(PaymentService service) {
		this.service = service;
	}

	public boolean processPayment(Payment payment) {
		return service.processPayment(payment);
	}
}