package cancellation;

import repository.PaymentRepository;

public class PaymentExistsHandler extends OrderCancellationHandler {

	private final PaymentRepository repository;

	public PaymentExistsHandler(PaymentRepository repository) {
		this.repository = repository;
	}

	@Override
	protected void validate(CancellationContext context) {

		String paymentCode = context.getPaymentCode();

		if (paymentCode == null || paymentCode.isBlank()) {
			throw new IllegalArgumentException("Payment code cannot be empty.");
		}

		context.setPayment(repository.findByCode(paymentCode)
				.orElseThrow(() -> new IllegalArgumentException("Payment not found.")));
	}
}