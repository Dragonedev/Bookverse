package entities;

public class Product {

	private String productCode;
	private String name;
	private Double price;
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
		private Integer quantity;

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
			return new Product(this);
		}
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
