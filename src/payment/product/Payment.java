package payment.product;

import entities.Order;

public abstract class Payment {

	private String paymentCode;
	private Order order;
	private Double amount;
	private String currency;

	protected Payment(String paymentCode, Order order, Double amount, String currency) {
		this.paymentCode = paymentCode;
		this.order = order;
		this.amount = amount;
		this.currency = currency;
	}

	public abstract String getPaymentMethod();

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