package payment.strategy;

import payment.product.Payment;

public class PixRefundStrategy implements RefundStrategy {

	@Override
	public boolean refund(Payment payment) {

		if (!"PIX".equals(payment.getPaymentMethod())) {
			throw new IllegalArgumentException("Payment method must be PIX.");
		}

		System.out.println("Processing Pix refund...");

		return true;
	}
}