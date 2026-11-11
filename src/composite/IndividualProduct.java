package composite;

import java.util.Map;

import entities.Product;
import exceptions.InvalidDataException;

public class IndividualProduct implements ProductComponent {

	private final Product product;

	public IndividualProduct(Product product) {

		if (product == null) {
			throw new InvalidDataException("Product cannot be null.");
		}

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

	@Override
	public void collectStock(int quantity, Map<Product, Integer> stockByProduct) {
		if (stockByProduct.containsKey(product)) {
			stockByProduct.put(product, stockByProduct.get(product) + quantity);
		} else {
			stockByProduct.put(product, quantity);
		}
	}

	public Product getProduct() {
		return product;
	}

}
