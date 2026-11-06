package cancellation;

import entities.enums.OrderStatus;

public class OrderStatusHandler extends OrderCancellationHandler {

	@Override
	protected void validate(CancellationContext context) {

		OrderStatus orderStatus = context.getOrder().getStatus();

		String paymentStatus = context.getPayment().getStatus();

		if (orderStatus == OrderStatus.CANCELLED) {
			throw new IllegalStateException("Order is already cancelled.");
		}

		if (orderStatus == OrderStatus.CREATED && !"PENDING".equals(paymentStatus)) {
			throw new IllegalStateException("Created order must have a pending payment.");
		}

		if (orderStatus == OrderStatus.COMPLETED && !"COMPLETED".equals(paymentStatus)) {
			throw new IllegalStateException("Completed order must have a completed payment.");
		}
	}
}