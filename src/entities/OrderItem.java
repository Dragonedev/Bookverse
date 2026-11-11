package entities;

import composite.ProductComponent;
import exceptions.InvalidDataException;

public class OrderItem {

	private final ProductComponent product;

	private final Integer quantity;

	private final Double unitPrice;

	private OrderItem(Builder builder) {

		this.product = builder.product;

		this.quantity = builder.quantity;

		this.unitPrice = builder.product.calculatePrice();
	}

	public static class Builder {

		private ProductComponent product;

		private Integer quantity;

		public Builder product(ProductComponent product) {

			this.product = product;

			return this;
		}

		public Builder quantity(Integer quantity) {

			this.quantity = quantity;

			return this;
		}

		public OrderItem build() {

			if (product == null) {
				throw new InvalidDataException("Order item product is required.");
			}

			if (quantity == null || quantity <= 0) {
				throw new InvalidDataException("Order item quantity must be greater than zero.");
			}

			return new OrderItem(this);
		}
	}

	public Double calculateSubtotal() {

		return unitPrice * quantity;
	}

	public ProductComponent getProduct() {

		return product;
	}

	public Integer getQuantity() {

		return quantity;
	}

	public Double getUnitPrice() {

		return unitPrice;
	}

}
