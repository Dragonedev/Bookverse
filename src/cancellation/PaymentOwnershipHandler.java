package cancellation;

public class PaymentOwnershipHandler extends OrderCancellationHandler {

	@Override
	protected void validate(CancellationContext context) {

		if (context.getPayment().getOrder() == null) {
			throw new IllegalStateException("Payment has no associated order.");
		}

		if (!context.getOrder().getOrderCode().equals(context.getPayment().getOrder().getOrderCode())) {

			throw new IllegalStateException("Payment does not belong to this order.");
		}
	}
}