package payment.adapter;

import payment.product.Payment;

public class DollarPaymentAdapter implements PaymentGateway {

	private final DollarPaymentProvider provider;

	public DollarPaymentAdapter(DollarPaymentProvider provider) {
		this.provider = provider;
	}

	@Override
	public boolean processPayment(Payment payment) {

		validateCurrency(payment);

		long amountInCents = Math.round(payment.getAmount() * 100);

		return provider.makeDollarPayment(amountInCents, payment.getPaymentCode());
	}

	@Override
	public boolean refundPayment(Payment payment) {

		validateCurrency(payment);

		long amountInCents = Math.round(payment.getAmount() * 100);

		return provider.refundDollarPayment(amountInCents, payment.getPaymentCode());
	}

	private void validateCurrency(Payment payment) {

		if (!"USD".equalsIgnoreCase(payment.getCurrency())) {
			throw new IllegalArgumentException("Dollar provider only accepts USD payments.");
		}
	}
}