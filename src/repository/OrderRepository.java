package repository;

import java.util.HashMap;
import java.util.Map;

import entities.Order;

public class OrderRepository {

	private Map<String, Order> orders = new HashMap<>();

	public void save(Order order) {
		orders.put(order.getOrderCode(), order);
	}

	public Order findByCode(String orderCode) {
		return orders.get(orderCode);
	}

	public void delete(String orderCode) {
		orders.remove(orderCode);
	}

	public boolean existsByCode(String orderCode) {
		return orders.containsKey(orderCode);
	}
}