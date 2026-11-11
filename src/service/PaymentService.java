package service;

import java.util.Map;

import payment.product.Payment;
import payment.strategy.PaymentStrategy;
import payment.strategy.RefundStrategy;
import repository.PaymentRepository;

public class PaymentService {

	private final PaymentRepository repository;

	private final Map<String, PaymentStrategy> strategies;

	private final Map<String, RefundStrategy> refundStrategies;

	public PaymentService(PaymentRepository repository, Map<String, PaymentStrategy> strategies,
			Map<String, RefundStrategy> refundStrategies) {

		this.repository = repository;
		this.strategies = Map.copyOf(strategies);
		this.refundStrategies = Map.copyOf(refundStrategies);
	}

	public void validateNewPayment(Payment payment) {

		validatePayment(payment);

		if (repository.existsByCode(payment.getPaymentCode())) {
			throw new IllegalStateException("Payment already exists.");
		}
	}

	public boolean processPayment(Payment payment) {

		validateNewPayment(payment);

		PaymentStrategy strategy = strategies.get(payment.getPaymentMethod());

		if (strategy == null) {
			throw new IllegalArgumentException("Payment strategy not found.");
		}

		repository.save(payment);

		boolean approved = strategy.process(payment);

		if (approved) {
			payment.markAsCompleted();
		}

		repository.save(payment);

		return approved;
	}

	public boolean cancelOrRefund(Payment payment) {

		if (payment == null) {
			throw new IllegalArgumentException("Payment cannot be null.");
		}

		RefundStrategy refundStrategy = refundStrategies.get(payment.getPaymentMethod());

		payment.cancelOrRefund(refundStrategy);

		repository.save(payment);

		return true;
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