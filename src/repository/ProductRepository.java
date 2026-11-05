package repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import entities.Product;

public class ProductRepository {

	private final Map<String, Product> products = new HashMap<>();

	public void save(Product product) {

		products.put(product.getProductCode(), product);

	}

	public Optional<Product> findByCode(String productCode) {

		return Optional.ofNullable(products.get(productCode));

	}

	public void delete(String productCode) {

		products.remove(productCode);

	}

	public boolean existsByCode(String productCode) {

		return products.containsKey(productCode);

	}

}