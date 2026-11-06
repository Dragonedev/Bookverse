package payment.strategy;

import payment.product.Payment;

public class CardRefundStrategy implements RefundStrategy {

	@Override
	public boolean refund(Payment payment) {

		if (!"CREDIT_CARD".equals(payment.getPaymentMethod())) {
			throw new IllegalArgumentException("Payment method must be CREDIT_CARD.");
		}

		System.out.println("Processing credit card refund...");

		return true;
	}
}