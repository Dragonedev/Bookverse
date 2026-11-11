package payment.factory;

import entities.Order;
import payment.product.Payment;

public abstract class PaymentFactory {

	public final Payment createPayment(String paymentCode, Order order) {

		if (order == null) {
			throw new IllegalArgumentException("Order cannot be null.");
		}

		return instantiatePayment(paymentCode, order, order.calculateTotal());
	}

	protected abstract Payment instantiatePayment(String paymentCode, Order order, Double amount);
}
