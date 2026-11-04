package service;

import entities.Product;
import exceptions.DuplicateResourceException;
import exceptions.InvalidDataException;
import exceptions.ResourceNotFoundException;
import repository.ProductRepository;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product) {

        if (product == null) {
            throw new InvalidDataException("Product cannot be null.");
        }

        if (product.getProductCode() == null ||
                product.getProductCode().isBlank()) {
            throw new InvalidDataException("Product code is required.");
        }

        if (product.getName() == null || product.getName().isBlank()) {
            throw new InvalidDataException("Product name is required.");
        }

        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new InvalidDataException("Product price must be greater than zero.");
        }

        if (product.getQuantity() == null || product.getQuantity() < 0) {
            throw new InvalidDataException("Product quantity cannot be negative.");
        }

        if (productRepository.existsByCode(product.getProductCode())) {
            throw new DuplicateResourceException("Product already exists.");
        }

        productRepository.save(product);
    }

    public Product findByCode(String productCode) {

        if (productCode == null || productCode.isBlank()) {
            throw new InvalidDataException("Product code is required.");
        }

        return productRepository.findByCode(productCode)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));
    }

    public void delete(String productCode) {

        findByCode(productCode);
        productRepository.delete(productCode);
    }

    public boolean existsByCode(String productCode) {

        if (productCode == null || productCode.isBlank()) {
            throw new InvalidDataException("Product code is required.");
        }

        return productRepository.existsByCode(productCode);
    }
}