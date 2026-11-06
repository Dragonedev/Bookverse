package payment.state;

public class CancelledPaymentState implements PaymentState {

	@Override
	public PaymentState complete() {
		throw new IllegalStateException("Cancelled payment cannot be completed.");
	}

	@Override
	public PaymentState cancel() {
		throw new IllegalStateException("Payment is already cancelled.");
	}

	@Override
	public PaymentState refund() {
		throw new IllegalStateException("Cancelled payment cannot be refunded.");
	}

	@Override
	public String getStatus() {
		return "CANCELLED";
	}

}