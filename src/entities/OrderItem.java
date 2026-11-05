package entities;

import composite.ProductComponent;

public class OrderItem {

	private ProductComponent product;

	private Integer quantity;

	private Double unitPrice;

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

	public void setProduct(ProductComponent product) {

		this.product = product;

		this.unitPrice = product.calculatePrice();

	}

	public void setQuantity(Integer quantity) {

		this.quantity = quantity;

	}

	public void setUnitPrice(Double unitPrice) {

		this.unitPrice = unitPrice;

	}

}