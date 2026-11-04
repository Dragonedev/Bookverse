package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

	private String orderCode;
	private List<OrderItem> items = new ArrayList<>();

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

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

}