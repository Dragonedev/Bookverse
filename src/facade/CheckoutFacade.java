package facade;

import entities.Order;
import payment.factory.PaymentFactory;
import payment.product.Payment;
import service.InventoryService;
import service.OrderService;
import service.PaymentService;

public class CheckoutFacade {

	private final OrderService orderService;

	private final PaymentService paymentService;

	private final InventoryService inventoryService;

	public CheckoutFacade(OrderService orderService, PaymentService paymentService, InventoryService inventoryService) {

		this.orderService = orderService;
		this.paymentService = paymentService;
		this.inventoryService = inventoryService;
	}

	public Payment checkout(Order order, PaymentFactory paymentFactory, String paymentCode) {

		if (paymentFactory == null) {
			throw new IllegalArgumentException("Payment factory cannot be null.");
		}

		orderService.validateForCheckout(order);

		inventoryService.validateAvailability(order.getItems());

		Payment payment = paymentFactory.createPayment(paymentCode, order);

		paymentService.validateNewPayment(payment);

		orderService.save(order);

		boolean approved = paymentService.processPayment(payment);

		if (!approved) {
			return payment;
		}

		boolean stockDeducted = false;

		try {

			inventoryService.decreaseStock(order.getItems());

			stockDeducted = true;

			order.complete();

			orderService.update(order);

		} catch (RuntimeException exception) {

			if (stockDeducted) {
				inventoryService.restoreStock(order.getItems());
			}

			boolean refunded = paymentService.cancelOrRefund(payment);

			if (refunded) {
				order.cancel();
				orderService.update(order);
			}

			throw new IllegalStateException("Checkout failed. Payment compensation was attempted.", exception);
		}

		return payment;
	}
}