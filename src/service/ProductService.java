package service;

import entities.Product;
import repository.ProductRepository;

public class ProductService {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public void save(Product product) {
		productRepository.save(product);
	}

	public Product findByCode(String productCode) {
		return productRepository.findByCode(productCode);
	}

	public void delete(String productCode) {
		productRepository.delete(productCode);
	}

	public boolean existsByCode(String productCode) {
		return productRepository.existsByCode(productCode);
	}
}