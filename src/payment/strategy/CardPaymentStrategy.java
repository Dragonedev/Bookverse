package payment.strategy;

import payment.product.Payment;

public class CardPaymentStrategy implements PaymentStrategy {

	@Override
	public boolean process(Payment payment) {

		if (!"CREDIT_CARD".equals(payment.getPaymentMethod())) {
			throw new IllegalArgumentException("Payment method must be CREDIT_CARD.");
		}

		if (!"BRL".equalsIgnoreCase(payment.getCurrency())) {
			throw new IllegalArgumentException("Card payments must use BRL.");
		}

		System.out.println("Processing credit card payment...");

		return true;
	}

}