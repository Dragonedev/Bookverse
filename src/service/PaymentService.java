package service;

import java.util.Map;

import payment.product.Payment;
import payment.strategy.PaymentStrategy;
import repository.PaymentRepository;

public class PaymentService {

	private final PaymentRepository repository;

	private final Map<String, PaymentStrategy> strategies;

	public PaymentService(PaymentRepository repository, Map<String, PaymentStrategy> strategies) {

		this.repository = repository;
		this.strategies = Map.copyOf(strategies);
	}

	public boolean processPayment(Payment payment) {

		validatePayment(payment);

		if (!"PENDING".equals(payment.getStatus())) {
			throw new IllegalStateException("Only pending payments can be processed.");
		}

		if (repository.existsByCode(payment.getPaymentCode())) {
			throw new IllegalStateException("Payment already exists.");
		}

		PaymentStrategy strategy = strategies.get(payment.getPaymentMethod());

		if (strategy == null) {
			throw new IllegalArgumentException("Payment strategy not found.");
		}

		boolean approved = strategy.process(payment);

		if (approved) {
			payment.markAsCompleted();
			repository.save(payment);
		}

		return approved;
	}

	private void validatePayment(Payment payment) {

		if (payment == null) {
			throw new IllegalArgumentException("Payment cannot be null.");
		}

		if (payment.getPaymentCode() == null || payment.getPaymentCode().isBlank()) {
			throw new IllegalArgumentException("Payment code cannot be empty.");
		}

		if (payment.getOrder() == null || payment.getOrder().getItems().isEmpty()) {
			throw new IllegalArgumentException("Payment must have a valid order.");
		}

		if (payment.getAmount() == null || payment.getAmount() <= 0) {
			throw new IllegalArgumentException("Payment amount must be greater than zero.");
		}

		if (payment.getCurrency() == null || payment.getCurrency().isBlank()) {
			throw new IllegalArgumentException("Payment currency cannot be empty.");
		}

		if (payment.getPaymentMethod() == null || payment.getPaymentMethod().isBlank()) {
			throw new IllegalArgumentException("Payment method cannot be empty.");
		}
	}

}