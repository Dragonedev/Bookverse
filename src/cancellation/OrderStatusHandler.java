package cancellation;

import entities.enums.OrderStatus;

public class OrderStatusHandler extends OrderCancellationHandler {

	@Override
	protected void validate(CancellationContext context) {

		if (context.getOrder().getStatus() == OrderStatus.CANCELLED) {
			throw new IllegalStateException("Order is already cancelled.");
		}
	}
}
