package cancellation;

import entities.Order;
import payment.product.Payment;

public class PaymentOwnershipHandler extends OrderCancellationHandler {

	@Override
	protected void validate(CancellationContext context) {

		Order order = context.getOrder();
		Payment payment = context.getPayment();

		if (payment.getOrder() == null) {
			throw new IllegalStateException("Payment has no associated order.");
		}

		if (!order.getOrderCode().equals(payment.getOrder().getOrderCode())) {
			throw new IllegalStateException("Payment does not belong to this order.");
		}
	}
}
