package service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import composite.IndividualProduct;
import composite.ProductBundle;
import composite.ProductComponent;
import entities.OrderItem;
import entities.Product;

public class InventoryService {

	private static class StockRequirement {

		private Product product;

		private Integer quantity;

		public StockRequirement(Product product, Integer quantity) {
			this.product = product;
			this.quantity = quantity;
		}

		public Product getProduct() {
			return product;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void addQuantity(Integer quantity) {
			this.quantity += quantity;
		}
	}

	public void validateAvailability(List<OrderItem> items) {

		Map<String, StockRequirement> requirements = collectRequirements(items);

		for (StockRequirement requirement : requirements.values()) {

			Product product = requirement.getProduct();

			if (product.getQuantity() < requirement.getQuantity()) {
				throw new IllegalStateException("Insufficient stock for product: " + product.getName());
			}
		}
	}

	public void decreaseStock(List<OrderItem> items) {

		validateAvailability(items);

		Map<String, StockRequirement> requirements = collectRequirements(items);

		for (StockRequirement requirement : requirements.values()) {

			Product product = requirement.getProduct();

			product.setQuantity(product.getQuantity() - requirement.getQuantity());
		}
	}

	public void restoreStock(List<OrderItem> items) {

		Map<String, StockRequirement> requirements = collectRequirements(items);

		for (StockRequirement requirement : requirements.values()) {

			Product product = requirement.getProduct();

			product.setQuantity(product.getQuantity() + requirement.getQuantity());
		}
	}

	private Map<String, StockRequirement> collectRequirements(List<OrderItem> items) {

		Map<String, StockRequirement> requirements = new LinkedHashMap<>();

		for (OrderItem item : items) {

			if (item.getQuantity() == null || item.getQuantity() <= 0) {
				throw new IllegalArgumentException("Invalid order item quantity.");
			}

			collectProduct(item.getProduct(), item.getQuantity(), requirements);
		}

		return requirements;
	}

	private void collectProduct(ProductComponent component, Integer quantity,
			Map<String, StockRequirement> requirements) {

		if (component instanceof IndividualProduct) {

			Product product = ((IndividualProduct) component).getProduct();

			String code = product.getProductCode();

			StockRequirement requirement = requirements.get(code);

			if (requirement == null) {

				requirements.put(code, new StockRequirement(product, quantity));

			} else {

				requirement.addQuantity(quantity);
			}

		} else if (component instanceof ProductBundle) {

			ProductBundle bundle = (ProductBundle) component;

			for (ProductComponent child : bundle.getComponents()) {

				collectProduct(child, quantity, requirements);

			}

		} else {

			throw new IllegalArgumentException("Unknown product component.");
		}
	}
}