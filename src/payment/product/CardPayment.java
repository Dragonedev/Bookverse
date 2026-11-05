package payment.product;

import entities.Order;

public class CardPayment extends Payment {

	public CardPayment(String paymentCode, Order order, Double amount, String currency) {
		super(paymentCode, order, amount, currency);
	}

	@Override
	public String getPaymentMethod() {
		return "CREDIT_CARD";
	}
}