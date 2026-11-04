package service;

import entities.Order;
import repository.OrderRepository;

public class OrderService {

	private OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public void save(Order order) {
		orderRepository.save(order);
	}

	public Order findByCode(String orderCode) {
		return orderRepository.findByCode(orderCode);
	}

	public void delete(String orderCode) {
		orderRepository.delete(orderCode);
	}

	public boolean existsByCode(String orderCode) {
		return orderRepository.existsByCode(orderCode);
	}
}