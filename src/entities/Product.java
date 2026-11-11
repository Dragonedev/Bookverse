package entities;

import exceptions.InsufficientStockException;
import exceptions.InvalidDataException;

public class Product {

	private final String productCode;
	private final String name;
	private final Double price;
	private Integer quantity;

	private Product(Builder builder) {
		this.productCode = builder.productCode;
		this.name = builder.name;
		this.price = builder.price;
		this.quantity = builder.quantity;
	}

	public static class Builder {
		private String productCode;
		private String name;
		private Double price;
		private Integer quantity = 0;

		public Builder productCode(String productCode) {
			this.productCode = productCode;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder price(Double price) {
			this.price = price;
			return this;
		}

		public Builder quantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}

		public Product build() {

			if (productCode == null || productCode.isBlank()) {
				throw new InvalidDataException("Product code is required.");
			}

			if (name == null || name.isBlank()) {
				throw new InvalidDataException("Product name is required.");
			}

			if (price == null || price <= 0) {
				throw new InvalidDataException("Product price must be greater than zero.");
			}

			if (quantity == null || quantity < 0) {
				throw new InvalidDataException("Product quantity cannot be negative.");
			}

			return new Product(this);
		}
	}

	public boolean hasStock(int amount) {
		return quantity >= amount;
	}

	public void decreaseStock(int amount) {

		validateAmount(amount);

		if (!hasStock(amount)) {
			throw new InsufficientStockException("Insufficient stock for product: " + name);
		}

		quantity -= amount;
	}

	public void increaseStock(int amount) {

		validateAmount(amount);

		quantity += amount;
	}

	private void validateAmount(int amount) {
		if (amount <= 0) {
			throw new InvalidDataException("Amount must be greater than zero.");
		}
	}

	public String getProductCode() {
		return productCode;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

}
