package controller;

import entities.Product;
import service.ProductService;

public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void createProduct(Product product) {
        productService.save(product);
    }

    public Product findProduct(String productCode) {
        return productService.findByCode(productCode);
    }

    public void deleteProduct(String productCode) {
        productService.delete(productCode);
    }

    public boolean productExists(String productCode) {
        return productService.existsByCode(productCode);
    }
}