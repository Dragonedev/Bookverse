package cancellation;

public class PaymentStatusHandler extends OrderCancellationHandler {

	@Override
	protected void validate(CancellationContext context) {

		String status = context.getPayment().getStatus();

		if (!"PENDING".equals(status) && !"COMPLETED".equals(status)) {

			throw new IllegalStateException("Payment cannot be cancelled or refunded.");
		}
	}
}