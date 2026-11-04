package controller;

import entities.Order;
import service.OrderService;

public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	public void createOrder(Order order) {
		orderService.save(order);
	}

	public Order findOrder(String orderCode) {
		return orderService.findByCode(orderCode);
	}

	public void deleteOrder(String orderCode) {
		orderService.delete(orderCode);
	}

	public boolean orderExists(String orderCode) {
		return orderService.existsByCode(orderCode);
	}
}