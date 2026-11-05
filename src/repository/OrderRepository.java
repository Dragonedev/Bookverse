package repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import entities.Order;

public class OrderRepository {

	private final Map<String, Order> orders = new HashMap<>();

	public void save(Order order) {

		orders.put(order.getOrderCode(), order);

	}

	public Optional<Order> findByCode(String orderCode) {

		return Optional.ofNullable(orders.get(orderCode));

	}

	public void delete(String orderCode) {

		orders.remove(orderCode);

	}

	public boolean existsByCode(String orderCode) {

		return orders.containsKey(orderCode);

	}

}