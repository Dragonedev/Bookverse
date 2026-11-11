package payment.product;

import entities.Order;

public class CardPayment extends Payment {

	public CardPayment(String paymentCode, Order order, Double amount) {
		super(paymentCode, order, amount, "BRL");
	}

	@Override
	public String getPaymentMethod() {
		return "CREDIT_CARD";
	}
}
