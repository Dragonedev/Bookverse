package payment.product;

import entities.Order;

public class PixPayment extends Payment {

	public PixPayment(String paymentCode, Order order, Double amount, String currency) {
		super(paymentCode, order, amount, currency);
	}

	@Override
	public String getPaymentMethod() {
		return "PIX";
	}
}