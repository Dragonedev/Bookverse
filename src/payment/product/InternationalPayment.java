package payment.product;

import entities.Order;

public class InternationalPayment extends Payment {

	public InternationalPayment(String paymentCode, Order order, Double amount) {
		super(paymentCode, order, amount, "USD");
	}

	@Override
	public String getPaymentMethod() {
		return "INTERNATIONAL";
	}
}
