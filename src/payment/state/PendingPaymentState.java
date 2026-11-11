package payment.state;

import entities.enums.OrderStatus;
import payment.product.Payment;
import payment.strategy.RefundStrategy;

public class PendingPaymentState implements PaymentState {

	@Override
	public PaymentState complete() {
		return new CompletedPaymentState();
	}

	@Override
	public PaymentState cancelOrRefund(Payment payment, RefundStrategy refundStrategy) {
		return new CancelledPaymentState();
	}

	@Override
	public boolean canBeCancelledOrRefunded() {
		return true;
	}

	@Override
	public boolean isConsistentWith(OrderStatus orderStatus) {
		return orderStatus == OrderStatus.CREATED;
	}

	@Override
	public PaymentStatus getStatus() {
		return PaymentStatus.PENDING;
	}

}
