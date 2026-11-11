package payment.state;

import entities.enums.OrderStatus;
import payment.product.Payment;
import payment.strategy.RefundStrategy;

public class CancelledPaymentState implements PaymentState {

	@Override
	public PaymentState complete() {
		throw new IllegalStateException("Cancelled payment cannot be completed.");
	}

	@Override
	public PaymentState cancelOrRefund(Payment payment, RefundStrategy refundStrategy) {
		throw new IllegalStateException("Payment is already cancelled.");
	}

	@Override
	public boolean canBeCancelledOrRefunded() {
		return false;
	}

	@Override
	public boolean isConsistentWith(OrderStatus orderStatus) {
		return orderStatus == OrderStatus.CANCELLED;
	}

	@Override
	public PaymentStatus getStatus() {
		return PaymentStatus.CANCELLED;
	}

}
