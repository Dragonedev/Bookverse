package composite;

import entities.Product;

public class IndividualProduct implements ProductComponent {

	private final Product product;

	public IndividualProduct(Product product) {
		this.product = product;
	}

	@Override
	public String getName() {
		return product.getName();
	}

	@Override
	public Double calculatePrice() {
		return product.getPrice();
	}

}