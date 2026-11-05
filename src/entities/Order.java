package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

	private String orderCode;

	private List<OrderItem> items = new ArrayList<>();

	private Order(Builder builder) {
		this.orderCode = builder.orderCode;
		this.items = new ArrayList<>(builder.items);
	}

	public static class Builder {

		private String orderCode;

		private List<OrderItem> items = new ArrayList<>();

		public Builder orderCode(String orderCode) {
			this.orderCode = orderCode;
			return this;
		}

		public Builder addItem(OrderItem item) {
			this.items.add(item);
			return this;
		}

		public Builder items(List<OrderItem> items) {
			this.items = new ArrayList<>(items);
			return this;
		}

		public Order build() {
			return new Order(this);
		}
	}

	public void addItem(OrderItem item) {
		items.add(item);
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

}