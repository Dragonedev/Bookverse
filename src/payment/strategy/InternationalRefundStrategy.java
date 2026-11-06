package payment.strategy;

import payment.adapter.PaymentGateway;
import payment.product.Payment;

public class InternationalRefundStrategy implements RefundStrategy {

	private final PaymentGateway gateway;

	public InternationalRefundStrategy(PaymentGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public boolean refund(Payment payment) {

		if (!"INTERNATIONAL".equals(payment.getPaymentMethod())) {
			throw new IllegalArgumentException("Payment method must be INTERNATIONAL.");
		}

		return gateway.refundPayment(payment);
	}
}	