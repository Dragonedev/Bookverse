package composite;

import java.util.Map;

import entities.Product;

public interface ProductComponent {

	String getName();

	Double calculatePrice();

	void collectStock(int quantity, Map<Product, Integer> stockByProduct);

}
