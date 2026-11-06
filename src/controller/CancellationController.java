package controller;

import cancellation.OrderCancellationService;
import payment.product.Payment;

public class CancellationController {

	private final OrderCancellationService service;

	public CancellationController(OrderCancellationService service) {
		this.service = service;
	}

	public Payment cancelOrder(String orderCode, String paymentCode) {

		return service.cancelOrder(orderCode, paymentCode);
	}
}