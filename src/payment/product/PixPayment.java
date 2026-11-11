package payment.product;

import entities.Order;

public class PixPayment extends Payment {

	public PixPayment(String paymentCode, Order order, Double amount) {
		super(paymentCode, order, amount, "BRL");
	}

	@Override
	public String getPaymentMethod() {
		return "PIX";
	}
}
