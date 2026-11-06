package payment.state;

public class RefundedPaymentState implements PaymentState {

	@Override
	public PaymentState complete() {
		throw new IllegalStateException("Refunded payment cannot be completed.");
	}

	@Override
	public PaymentState cancel() {
		throw new IllegalStateException("Refunded payment cannot be cancelled.");
	}

	@Override
	public PaymentState refund() {
		throw new IllegalStateException("Payment is already refunded.");
	}

	@Override
	public String getStatus() {
		return "REFUNDED";
	}

}