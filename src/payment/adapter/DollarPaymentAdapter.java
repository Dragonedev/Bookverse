package payment.adapter;

import payment.product.Payment;

public class DollarPaymentAdapter implements PaymentGateway {

	private final DollarPaymentProvider provider;

	public DollarPaymentAdapter(DollarPaymentProvider provider) {
		this.provider = provider;
	}

	@Override
	public boolean processPayment(Payment payment) {

		if (!"USD".equalsIgnoreCase(payment.getCurrency())) {
			throw new IllegalArgumentException("Dollar provider only accepts USD payments.");
		}

		long amountInCents = Math.round(payment.getAmount() * 100);

		return provider.makeDollarPayment(amountInCents, payment.getPaymentCode());
	}

}