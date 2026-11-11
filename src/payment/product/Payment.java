package payment.product;

import entities.Order;
import entities.enums.OrderStatus;
import payment.state.PaymentState;
import payment.state.PaymentStatus;
import payment.state.PendingPaymentState;
import payment.strategy.RefundStrategy;

public abstract class Payment {

	private final String paymentCode;

	private final Order order;

	private final Double amount;

	private final String currency;

	private PaymentState state;

	protected Payment(String paymentCode, Order order, Double amount, String currency) {

		this.paymentCode = paymentCode;
		this.order = order;
		this.amount = amount;
		this.currency = currency;

		this.state = new PendingPaymentState();
	}

	public abstract String getPaymentMethod();

	public void markAsCompleted() {
		state = state.complete();
	}

	public void cancelOrRefund(RefundStrategy refundStrategy) {
		state = state.cancelOrRefund(this, refundStrategy);
	}

	public boolean canBeCancelledOrRefunded() {
		return state.canBeCancelledOrRefunded();
	}

	public boolean isConsistentWith(OrderStatus orderStatus) {
		return state.isConsistentWith(orderStatus);
	}

	public PaymentStatus getStatus() {
		return state.getStatus();
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public Order getOrder() {
		return order;
	}

	public Double getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

}
