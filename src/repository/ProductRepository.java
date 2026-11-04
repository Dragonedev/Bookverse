package repository;

import java.util.HashMap;
import java.util.Map;

import entities.Product;

public class ProductRepository {

	private Map<String, Product> products = new HashMap<>();

	public void save(Product product) {
		products.put(product.getProductCode(), product);
	}

	public Product findByCode(String productCode) {
		return products.get(productCode);
	}

	public void delete(String productCode) {
		products.remove(productCode);
	}

	public boolean existsByCode(String productCode) {
		return products.containsKey(productCode);
	}
}