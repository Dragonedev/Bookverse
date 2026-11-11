package payment.state;

import entities.enums.OrderStatus;
import payment.product.Payment;
import payment.strategy.RefundStrategy;

public class CompletedPaymentState implements PaymentState {

	@Override
	public PaymentState complete() {
		throw new IllegalStateException("Payment is already completed.");
	}

	@Override
	public PaymentState cancelOrRefund(Payment payment, RefundStrategy refundStrategy) {

		if (refundStrategy == null) {
			throw new IllegalArgumentException("Refund strategy not found.");
		}

		if (!refundStrategy.refund(payment)) {
			throw new IllegalStateException("Refund failed.");
		}

		return new RefundedPaymentState();
	}

	@Override
	public boolean canBeCancelledOrRefunded() {
		return true;
	}

	@Override
	public boolean isConsistentWith(OrderStatus orderStatus) {
		return orderStatus == OrderStatus.COMPLETED;
	}

	@Override
	public PaymentStatus getStatus() {
		return PaymentStatus.COMPLETED;
	}

}
