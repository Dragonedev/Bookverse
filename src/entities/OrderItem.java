package entities;

public class OrderItem {

	private Product product;

	private Integer quantity;

	private Double unitPrice;

	private OrderItem(Builder builder) {
		this.product = builder.product;
		this.quantity = builder.quantity;
		this.unitPrice = builder.product.getPrice();
	}

	public static class Builder {

		private Product product;

		private Integer quantity;

		public Builder product(Product product) {
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

	public Product getProduct() {
		return product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}