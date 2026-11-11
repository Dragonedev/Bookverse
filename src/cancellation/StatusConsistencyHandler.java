package cancellation;

import entities.Order;
import payment.product.Payment;

public class StatusConsistencyHandler extends OrderCancellationHandler {

	@Override
	protected void validate(CancellationContext context) {

		Order order = context.getOrder();
		Payment payment = context.getPayment();

		if (!payment.isConsistentWith(order.getStatus())) {
			throw new IllegalStateException(
					"Order status " + order.getStatus() + " is not consistent with payment status " + payment.getStatus() + ".");
		}
	}
}
