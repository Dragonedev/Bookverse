package service;

import payment.adapter.PaymentGateway;
import payment.product.Payment;
import repository.PaymentRepository;

public class PaymentService {

	private final PaymentRepository repository;
	private final PaymentGateway gateway;

	public PaymentService(PaymentRepository repository, PaymentGateway gateway) {

		this.repository = repository;
		this.gateway = gateway;
	}

	public boolean processPayment(Payment payment) {

		validatePayment(payment);

		if (repository.existsByCode(payment.getPaymentCode())) {
			throw new IllegalStateException("Payment already exists.");
		}

		boolean approved = gateway.processPayment(payment);

		if (approved) {
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
	}
}