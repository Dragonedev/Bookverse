package payment.product;

import entities.Order;
import payment.state.PaymentState;
import payment.state.PendingPaymentState;

public abstract class Payment {

	private String paymentCode;

	private Order order;

	private Double amount;

	private String currency;

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

	public void cancelPayment() {
		state = state.cancel();
	}

	public void refundPayment() {
		state = state.refund();
	}

	public String getStatus() {
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