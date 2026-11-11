package service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import entities.OrderItem;
import entities.Product;
import exceptions.InsufficientStockException;

public class InventoryService {

	public void validateAvailability(List<OrderItem> items) {

		Map<Product, Integer> stockByProduct = collectStock(items);

		for (Product product : stockByProduct.keySet()) {

			if (!product.hasStock(stockByProduct.get(product))) {
				throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
			}
		}
	}

	public void decreaseStock(List<OrderItem> items) {

		validateAvailability(items);

		Map<Product, Integer> stockByProduct = collectStock(items);

		for (Product product : stockByProduct.keySet()) {
			product.decreaseStock(stockByProduct.get(product));
		}
	}

	public void restoreStock(List<OrderItem> items) {

		Map<Product, Integer> stockByProduct = collectStock(items);

		for (Product product : stockByProduct.keySet()) {
			product.increaseStock(stockByProduct.get(product));
		}
	}

	private Map<Product, Integer> collectStock(List<OrderItem> items) {

		Map<Product, Integer> stockByProduct = new LinkedHashMap<>();

		for (OrderItem item : items) {
			item.getProduct().collectStock(item.getQuantity(), stockByProduct);
		}

		return stockByProduct;
	}
}
