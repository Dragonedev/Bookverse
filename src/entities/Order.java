package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import composite.ProductComponent;
import entities.enums.OrderStatus;
import exceptions.EmptyOrderException;
import exceptions.InvalidDataException;

public class Order {

	private String orderCode;

	private List<OrderItem> items = new ArrayList<>();

	private OrderStatus status;

	private Order(Builder builder) {

		this.orderCode = builder.orderCode;

		this.items = new ArrayList<>(builder.items);

		this.status = OrderStatus.CREATED;
	}

	public static class Builder {

		private String orderCode;

		private List<OrderItem> items = new ArrayList<>();

		public Builder orderCode(String orderCode) {
			this.orderCode = orderCode;
			return this;
		}

		public Builder addItem(OrderItem item) {

			if (item == null) {
				throw new InvalidDataException("Order item cannot be null.");
			}

			this.items.add(item);
			return this;
		}

		public Builder addItem(ProductComponent product, int quantity) {
			return addItem(new OrderItem.Builder().product(product).quantity(quantity).build());
		}

		public Builder items(List<OrderItem> items) {
			this.items = new ArrayList<>(items);
			return this;
		}

		public Order build() {

			if (orderCode == null || orderCode.isBlank()) {
				throw new InvalidDataException("Order code is required.");
			}

			if (items.isEmpty()) {
				throw new EmptyOrderException("Order must have at least one item.");
			}

			return new Order(this);
		}
	}

	public void addItem(OrderItem item) {
		if (status != OrderStatus.CREATED) {
			throw new IllegalStateException("Cannot modify a completed or cancelled order.");
		}

		items.add(item);
	}

	public void complete() {

		if (status != OrderStatus.CREATED) {
			throw new IllegalStateException("Only created orders can be completed.");
		}

		status = OrderStatus.COMPLETED;
	}

	public void cancel() {

		if (status == OrderStatus.CANCELLED) {
			throw new IllegalStateException("Order is already cancelled.");
		}

		status = OrderStatus.CANCELLED;
	}

	public List<OrderItem> getItems() {
		return Collections.unmodifiableList(items);
	}

	public Double calculateTotal() {

		Double total = 0.0;

		for (OrderItem item : items) {
			total += item.calculateSubtotal();
		}

		return total;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public OrderStatus getStatus() {
		return status;
	}
}