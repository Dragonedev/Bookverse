package service;

import entities.Order;
import repository.OrderRepository;

public class OrderService {

	private final OrderRepository repository;

	public OrderService(OrderRepository repository) {
		this.repository = repository;
	}

	public void validateForCheckout(Order order) {

		if (order == null) {
			throw new IllegalArgumentException("Order cannot be null.");
		}

		if (order.getOrderCode() == null || order.getOrderCode().isBlank()) {
			throw new IllegalArgumentException("Order code cannot be empty.");
		}

		if (order.getItems().isEmpty()) {
			throw new IllegalArgumentException("Order cannot be empty.");
		}

		if (order.calculateTotal() <= 0) {
			throw new IllegalArgumentException("Order total must be greater than zero.");
		}

		if (repository.existsByCode(order.getOrderCode())) {
			throw new IllegalStateException("Order already exists.");
		}
	}

	public void save(Order order) {
		validateForCheckout(order);
		repository.save(order);
	}

	public void update(Order order) {

		if (order == null) {
			throw new IllegalArgumentException("Order cannot be null.");
		}

		if (!repository.existsByCode(order.getOrderCode())) {
			throw new IllegalArgumentException("Order not found.");
		}

		repository.save(order);
	}

	public Order findByCode(String orderCode) {

		return repository.findByCode(orderCode).orElseThrow(() -> new IllegalArgumentException("Order not found."));
	}

	public void delete(String orderCode) {

		if (!repository.existsByCode(orderCode)) {
			throw new IllegalArgumentException("Order not found.");
		}

		repository.delete(orderCode);
	}

	public boolean existsByCode(String orderCode) {
		return repository.existsByCode(orderCode);
	}
}