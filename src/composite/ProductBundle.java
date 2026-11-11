package composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import entities.Product;
import exceptions.InvalidDataException;

public class ProductBundle implements ProductComponent {

	private final String name;

	private final List<ProductComponent> components = new ArrayList<>();

	public ProductBundle(String name) {

		if (name == null || name.isBlank()) {
			throw new InvalidDataException("Bundle name is required.");
		}

		this.name = name;
	}

	public void add(ProductComponent component) {

		if (component == null) {
			throw new InvalidDataException("Component cannot be null.");
		}

		components.add(component);
	}

	public void add(Product product) {
		add(new IndividualProduct(product));
	}

	public void remove(ProductComponent component) {
		components.remove(component);
	}

	public List<ProductComponent> getComponents() {
		return Collections.unmodifiableList(components);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Double calculatePrice() {

		Double total = 0.0;

		for (ProductComponent component : components) {
			total += component.calculatePrice();
		}

		return total;
	}

	@Override
	public void collectStock(int quantity, Map<Product, Integer> stockByProduct) {

		for (ProductComponent component : components) {
			component.collectStock(quantity, stockByProduct);
		}
	}

}
