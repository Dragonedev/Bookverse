package payment.product;

import entities.Order;

public class InternationalPayment extends Payment {

	public InternationalPayment(String paymentCode, Order order, Double amount, String currency) {
		super(paymentCode, order, amount, currency);
	}

	@Override
	public String getPaymentMethod() {
		return "INTERNATIONAL";
	}
}