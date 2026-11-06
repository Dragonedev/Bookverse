package facade;

import entities.Order;
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

	public Payment checkout(Order order, PaymentFactory paymentFactory, String paymentCode, String currency) {

		orderService.validateForCheckout(order);

		Payment payment = paymentFactory.createPayment(paymentCode, order, order.calculateTotal(), currency);

		boolean approved = paymentService.processPayment(payment);

		if (!approved) {
			throw new IllegalStateException("Payment was not approved.");
		}

		orderService.save(order);

		return payment;
	}
}