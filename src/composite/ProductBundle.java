package composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductBundle implements ProductComponent {

	private final String name;

	private final List<ProductComponent> components = new ArrayList<>();

	public ProductBundle(String name) {
		this.name = name;
	}

	public void add(ProductComponent component) {
		components.add(component);
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

}