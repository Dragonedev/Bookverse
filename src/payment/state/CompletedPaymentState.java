package payment.state;

public class CompletedPaymentState implements PaymentState {

	@Override
	public PaymentState complete() {
		throw new IllegalStateException("Payment is already completed.");
	}

	@Override
	public PaymentState cancel() {
		throw new IllegalStateException("Completed payment cannot be cancelled. Use refund.");
	}

	@Override
	public PaymentState refund() {
		return new RefundedPaymentState();
	}

	@Override
	public String getStatus() {
		return "COMPLETED";
	}

}