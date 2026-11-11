package cancellation;

public class PaymentStatusHandler extends OrderCancellationHandler {

	@Override
	protected void validate(CancellationContext context) {

		if (!context.getPayment().canBeCancelledOrRefunded()) {
			throw new IllegalStateException("Payment cannot be cancelled or refunded.");
		}
	}
}
