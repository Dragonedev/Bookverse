package payment.strategy;

import payment.adapter.PaymentGateway;
import payment.product.Payment;

public class InternationalPaymentStrategy implements PaymentStrategy {

	private final PaymentGateway gateway;

	public InternationalPaymentStrategy(PaymentGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public boolean process(Payment payment) {

		if (!"INTERNATIONAL".equals(payment.getPaymentMethod())) {
			throw new IllegalArgumentException("Payment method must be INTERNATIONAL.");
		}

		if (!"USD".equalsIgnoreCase(payment.getCurrency())) {
			throw new IllegalArgumentException("International payments must use USD.");
		}

		return gateway.processPayment(payment);
	}

}