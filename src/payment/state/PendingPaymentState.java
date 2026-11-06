package payment.state;

public class PendingPaymentState implements PaymentState {

	@Override
	public PaymentState complete() {
		return new CompletedPaymentState();
	}

	@Override
	public PaymentState cancel() {
		return new CancelledPaymentState();
	}

	@Override
	public PaymentState refund() {
		throw new IllegalStateException("Pending payment cannot be refunded.");
	}

	@Override
	public String getStatus() {
		return "PENDING";
	}

}