package entities;

public class OrderItem {

	private Product product;
	private Integer quantity;
	private Double unitPrice;

	public OrderItem(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = product.getPrice();
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