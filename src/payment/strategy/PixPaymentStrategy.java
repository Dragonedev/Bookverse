package payment.strategy;

import payment.product.Payment;

public class PixPaymentStrategy implements PaymentStrategy {

	@Override
	public boolean process(Payment payment) {

		if (!"PIX".equals(payment.getPaymentMethod())) {
			throw new IllegalArgumentException("Payment method must be PIX.");
		}

		if (!"BRL".equalsIgnoreCase(payment.getCurrency())) {
			throw new IllegalArgumentException("Pix payments must use BRL.");
		}

		System.out.println("Processing Pix payment...");

		return true;
	}

}