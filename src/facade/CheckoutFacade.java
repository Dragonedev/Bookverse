package facade;

import entities.Order;
import payment.adapter.PaymentGateway;
import payment.factory.PaymentFactory;
import payment.product.Payment;
import service.OrderService;
import service.PaymentService;

public class CheckoutFacade {

	private final OrderService orderService;
	private final PaymentService paymentService;

	public CheckoutFacade(OrderService orderService, PaymentService paymentService) {

		this.orderService = orderService;
		this.paymentService = paymentService;
	}

	public Payment checkout(Order order, PaymentFactory paymentFactory, PaymentGateway paymentGateway,
			String paymentCode, String currency) {

		if (paymentFactory == null) {
			throw new IllegalArgumentException("Payment factory cannot be null.");
		}

		orderService.validateForCheckout(order);

		Double amount = order.calculateTotal();

		Payment payment = paymentFactory.createPayment(paymentCode, order, amount, currency);

		boolean approved = paymentService.processPayment(payment);

		if (!approved) {
			throw new IllegalStateException("Payment was not approved.");
		}

		orderService.save(order);

		return payment;
	}
}